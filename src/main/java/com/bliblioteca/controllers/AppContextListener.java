
package com.bliblioteca.controllers;

import com.biblioteca.utils.EncryptionUtil;
import static com.biblioteca.utils.EncryptionUtil.loadKey;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();
        try {
            SecretKey key = loadKey();
            sce.getServletContext().setAttribute("key", key);
        } catch (Exception ex) {
            Logger.getLogger(CreateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}