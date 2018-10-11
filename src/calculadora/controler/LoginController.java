/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora.controler;

import calculadora.model.Login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author 04018523262
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private Button btnOK;
    @FXML
    private Button btnCadastar;
    @FXML
    private PasswordField txtPass;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Entrar(ActionEvent event) throws IOException {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("Calculadora");
        EntityManager em = emf.createEntityManager(); 
        
        
        Query query = em.createQuery("SELECT n FROM Login AS n WHERE n.nome = :user");
       
        query.setParameter("user", txtNome.getText());
        query.setMaxResults(1);
        
        if(query.getResultList().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING); 
            alert.setHeaderText("usuario nao existe");
            alert.showAndWait();
        } else {
            Login n = (Login) query.getSingleResult();
            
            if(!n.getSenha().equals(txtPass.getText())){
                 Alert alert = new Alert(Alert.AlertType.WARNING); 
            alert.setHeaderText("senha errada");
            alert.showAndWait();
                
            } else {
       FXMLLoader fxmlLoader =new FXMLLoader();
                
        fxmlLoader.setLocation(getClass().getResource("/calculadora/fxml/calculadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage(); 
        stage.setTitle("calculadora");
        stage.setScene(scene);
        stage.show();
            }
        }
    }

    @FXML
    private void Cadastrar(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Calculadora");
        EntityManager em = emf.createEntityManager();
        Login login1 = new Login();
        
        
        login1.setNome(txtNome.getText());
        login1.setSenha(txtPass.getText());
        
        em.getTransaction().begin();
        
        em.persist(login1);
        
        em.getTransaction().commit();
    }
    
}
