import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import javafx.scene.control.Alert

class CalculadoraSoma : Application() {
    override fun start(primeiroEstagio: Stage) {
        val rotulo1 = Label("Digite o primeiro número:")
        val valor1 = TextField()

        val rotulo2 = Label("Digite o segundo número:")
        val valor2 = TextField()

        val calcula = Button("Calcular Soma")

        calcula.setOnAction {
            val num1 = valor1.text.toDoubleOrNull() ?: 0.0
            val num2 = valor2.text.toDoubleOrNull() ?: 0.0
            val soma = num1 + num2

            val alerta = Alert(Alert.AlertType.INFORMATION)
            alerta.title = "Resultado"
            alerta.headerText = null
            alerta.contentText = "A soma é: $soma"
            alerta.showAndWait()
        }

        val grade = GridPane()
        grade.addRow(0, rotulo1, valor1)
        grade.addRow(1, rotulo2, valor2)

        val vbox = VBox()
        vbox.children.addAll(grade, calcula)

        val cena = Scene(vbox, 300.0, 200.0)

        primeiroEstagio.title = "Calculadora de Soma"
        primeiroEstagio.scene = cena
        primeiroEstagio.show()
    }
}

fun main() {
    Application.launch(CalculadoraSoma::class.java)
}
