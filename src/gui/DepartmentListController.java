package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	private DepartmentService service;   //colocando o = new DepartmentService gera acoplamento forte, não é a melhor solução no caso, melhor usar um setter

	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	           
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	//vou carregar os departamentos nessa ObservableList e associar com o TableView
	private ObservableList<Department> obsList;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("OnBtNewAction");
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
		// injetar dependência - princípio SOLID - inversão de controle
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
		 inicializeNodes();
	}

	
	//método auxiliar para iniciar algum componente da tela
	private void inicializeNodes() {
		// comando para iniciar apropriadamente o comportamento das colunas da tabela
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//referenciar o Stage - usar downcast
		Stage stage = (Stage) Main.getMainScene().getWindow();
		//comando para a tabela acompanhar o tamanho da janela
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());		
	}
	
	//método responsável por acessar o serviço, carregar o departamento e colocar na ObservableList
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service wal null");
		}
		//vai recuperar os dados
		List<Department> list = service.findAll();
		//carregarar list na observableList (instancia a obslist pegando os dados originais da list
		obsList = FXCollections.observableArrayList(list);
		//carregar os itens na tableview e mostrar na tela
		tableViewDepartment.setItems(obsList);
		
	}

}
