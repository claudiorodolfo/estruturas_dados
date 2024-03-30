import javafx.application.Application
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import javafx.util.StringConverter
import java.time.LocalDate

class ExemploComponentesJavaFX : Application() {
    override fun start(janela: Stage) {
        val termos = CheckBox("Aceitar os termos")
        termos.isSelected = true

        val opcoes = ComboBox<String>()
        opcoes.items.addAll("Opção 1", "Opção 2", "Opção 3")
        opcoes.selectionModel.selectFirst()

        val imagem = ImageView(Image("https://img.lojadomecanico.com.br/256/46/448/154061/1608235424560.JPG"))

        val lista = ListView<String>()
        lista.items.addAll("Item 1", "Item 2", "Item 3")

        val senha = PasswordField()

        val grupo = ToggleGroup()
        val opcao1 = RadioButton("Opção 1")
        opcao1.toggleGroup = grupo
        val opcao2 = RadioButton("Opção 2")
        opcao2.toggleGroup = grupo
        grupo.selectToggle(opcao1)

        val textoLongo = TextArea()

        val tabela = TableView<Pessoa>()
        val coluna1 = TableColumn<Pessoa, String>("Nome:")
        coluna1.cellValueFactory = PropertyValueFactory<Pessoa, String>("nome")
        val coluna2 = TableColumn<Pessoa, Int>("Idade:")
        coluna2.cellValueFactory = PropertyValueFactory<Pessoa, Int>("idade")

        tabela.columns.addAll(coluna1, coluna2)
        tabela.items.addAll(
            Pessoa("João", 30),
            Pessoa("Maria", 40),
            Pessoa("José", 23)
        )

        val datas = DatePicker()
        datas.converter = object : StringConverter<LocalDate>() {
            override fun toString(UmaData: LocalDate?): String {
                return UmaData?.toString() ?: ""
            }

            override fun fromString(dataTextual: String?): LocalDate {
                return LocalDate.parse(dataTextual)
            }
        }

        val caixaVertical = VBox(5.0)
        caixaVertical.children.addAll(
            termos,
            HBox(10.0, Label("ComboBox:"), opcoes),
            HBox(10.0, Label("ImageView:"), imagem),
            HBox(10.0, Label("ListView:"), lista),
            HBox(10.0, Label("PasswordField:"), senha),
            HBox(10.0, Label("RadioButton:"), opcao1, opcao2),
            HBox(10.0, Label("TextArea:"), textoLongo),
            HBox(10.0, Label("TableView:"), tabela),            
            HBox(10.0, Label("DatePicker:"), datas)
        )
        caixaVertical.padding = Insets(20.0)

        val conteudo = Scene(caixaVertical, 600.0, 700.0)

        janela.title = "Exemplo Componentes JavaFX"
        janela.scene = conteudo
        //janela.width = 800.0
        //janela.height = 800.0
        janela.show()
    }
}

data class Pessoa(val nome: String, val idade: Int) {}

fun main() {
    Application.launch(ExemploComponentesJavaFX::class.java)
}