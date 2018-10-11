/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora.controler;

import calculadora.model.Historico;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author 04018523262
 */
public class calculadoraController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btnSoma;
    @FXML
    private Button btnSub;
    @FXML
    private Button btnMult;
    @FXML
    private Button btnDiv;
    @FXML
    private Button btnResu;
    @FXML
    private TextField txtNum1;
    @FXML
    private TextField txtNum2;
    @FXML
    private TextField txtResult;
    @FXML
    private Button btnHistorico;
    @FXML
    private TableView<?> table;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void Somar(ActionEvent event) {
        Double num1 = Double.parseDouble(txtNum1.getText());
         Double num2 = Double.parseDouble(txtNum2.getText());
         Double resultado = num1+num2;
         
        txtResult.setText(resultado.toString());
    }

    @FXML
    private void Subtrair(ActionEvent event) {
        
    }

    @FXML
    private void Multiplicar(ActionEvent event) {
        Double num1 = Double.parseDouble(txtNum1.getText());
        Double num2 = Double.parseDouble(txtNum2.getText());
         Double resultado = num1*num2;
         txtResult.setText(resultado.toString());
    }

    @FXML
    private void Dividir(ActionEvent event) {
         Double num1 = Double.parseDouble(txtNum1.getText());
        Double num2 = Double.parseDouble(txtNum2.getText());
         Double resultado = num1/num2;
         txtResult.setText(resultado.toString());
    }

    @FXML
    private void Resultado(ActionEvent event) {
        
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("Calculadora");
        EntityManager em = emf.createEntityManager();
        Historico calculadora1 = new Historico();
       
        
        calculadora1.setResult(txtResult.getText());
        
        
        em.getTransaction().begin();
        
        em.persist(calculadora1);
        
        em.getTransaction().commit();
    }


    @FXML
    private void Historico(ActionEvent event) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("Calculadora");
        EntityManager em = emf.createEntityManager();
        
         Query query = em.createQuery("SELECT h FROM Historico as h");
        
        List<Historico> historicos = query.getResultList();

        // Converte lista para observable list
        ObservableList ob = FXCollections.observableArrayList(historicos);
        // Adiciona resultado a Tabela
        table.setItems(ob);
        // Adiciona resultado ao Combobox
            

        em.close();
        emf.close();
    }
}

    
    

