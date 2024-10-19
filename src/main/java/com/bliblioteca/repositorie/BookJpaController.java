
package com.bliblioteca.repositorie;

import com.bliblioteca.models.Book;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bliblioteca.models.Loan;
import com.bliblioteca.repositorie.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;

public class BookJpaController implements Serializable {

    public BookJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BookJpaController(){
        emf = Persistence.createEntityManagerFactory("persistence");
    }
    
    public void create(Book book) {
        if (book.getLoans() == null) {
            book.setLoans(new ArrayList<Loan>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Loan> attachedLoans = new ArrayList<Loan>();
            for (Loan loansLoanToAttach : book.getLoans()) {
                loansLoanToAttach = em.getReference(loansLoanToAttach.getClass(), loansLoanToAttach.getId());
                attachedLoans.add(loansLoanToAttach);
            }
            book.setLoans(attachedLoans);
            em.persist(book);
            for (Loan loansLoan : book.getLoans()) {
                Book oldBookOfLoansLoan = loansLoan.getBook();
                loansLoan.setBook(book);
                loansLoan = em.merge(loansLoan);
                if (oldBookOfLoansLoan != null) {
                    oldBookOfLoansLoan.getLoans().remove(loansLoan);
                    oldBookOfLoansLoan = em.merge(oldBookOfLoansLoan);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Book book) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Book persistentBook = em.find(Book.class, book.getId());
            /*List<Loan> loansOld = persistentBook.getLoans();
            List<Loan> loansNew = book.getLoans();
            List<Loan> attachedLoansNew = new ArrayList<Loan>();
            for (Loan loansNewLoanToAttach : loansNew) {
                loansNewLoanToAttach = em.getReference(loansNewLoanToAttach.getClass(), loansNewLoanToAttach.getId());
                attachedLoansNew.add(loansNewLoanToAttach);
            }
            loansNew = attachedLoansNew;
            book.setLoans(loansNew);*/
            book = em.merge(book);
            /*for (Loan loansOldLoan : loansOld) {
                if (!loansNew.contains(loansOldLoan)) {
                    loansOldLoan.setBook(null);
                    loansOldLoan = em.merge(loansOldLoan);
                }
            }
            for (Loan loansNewLoan : loansNew) {
                if (!loansOld.contains(loansNewLoan)) {
                    Book oldBookOfLoansNewLoan = loansNewLoan.getBook();
                    loansNewLoan.setBook(book);
                    loansNewLoan = em.merge(loansNewLoan);
                    if (oldBookOfLoansNewLoan != null && !oldBookOfLoansNewLoan.equals(book)) {
                        oldBookOfLoansNewLoan.getLoans().remove(loansNewLoan);
                        oldBookOfLoansNewLoan = em.merge(oldBookOfLoansNewLoan);
                    }
                }
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = book.getId();
                if (findBook(id) == null) {
                    throw new NonexistentEntityException("The book with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Book book;
            try {
                book = em.getReference(Book.class, id);
                book.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The book with id " + id + " no longer exists.", enfe);
            }
            List<Loan> loans = book.getLoans();
            for (Loan loansLoan : loans) {
                loansLoan.setBook(null);
                loansLoan = em.merge(loansLoan);
            }
            em.remove(book);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Book> findBookEntities() {
        return findBookEntities(true, -1, -1);
    }

    public List<Book> findBookEntities(int maxResults, int firstResult) {
        return findBookEntities(false, maxResults, firstResult);
    }

    private List<Book> findBookEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Book.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    private List<Book> findBookEntities(boolean all, int maxResults, int firstResult,String where, String parameter) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Book> cq = builder.createQuery(Book.class);
            Root<Book> book = cq.from(Book.class);
            if ("title".equals(where)) {
                Expression<String> authorPath = book.get("title").as(String.class);
                cq.select(book).where(builder.like(authorPath, "%" + parameter + "%"));                //cq.select(book).where(builder.like((Path<String>) book.get("title"), "%" + parameter + "%"));
            } else if ("author".equals(where)) {
                Expression<String> authorPath = book.get("author").as(String.class);
                cq.select(book).where(builder.like(authorPath, "%" + parameter + "%"));            
            }else if ("gender".equals(where)) {
                Expression<String> authorPath = book.get("gender").as(String.class);
                cq.select(book).where(builder.like(authorPath, "%" + parameter + "%"));   
            }else {
                throw new IllegalArgumentException("Atributo no válido: " + where);
            }
            TypedQuery<Book> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    public Book findBook(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }
    public List<Book> findBooWithParameter(String where, String parameter) {
            return findBookEntities(true, -1, -1,where, parameter);
    }

    public int getBookCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Book> rt = cq.from(Book.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
