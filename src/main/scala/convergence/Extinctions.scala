package convergence

import org.scalajs.dom
import dom.html
import scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

@JSExport
object Extinctions{



  @JSExport
  def main(target: html.Div) = {

    val tInp = input(`type` := "text", style := "width:6em").render

    def t = tInp.value.toDouble

    val initPopInp = input(`type` := "text", style := "width:6em").render

    def initPop = initPopInp.value.toDouble

    val finalPopInp = input(`type` := "text", style := "width:6em").render

    def finalPop = finalPopInp.value.toDouble

    val lifespanInp = input(`type` := "text", style := "width:6em").render

    def lifespan = lifespanInp.value.toDouble

    def d = 1.0 / lifespan

    def rho = math.log(finalPop/initPop)

    def lambda = rho/t

    def b = lambda + d

    def extinctions = d * (finalPop - initPop)/ rho

    def transitions = b * t / 2

    val extOut = span("").render

    def setExt() = {
      extOut.textContent = extinctions.toString
    }

    val transOut = span("").render

    def setTrans() = {
      transOut.textContent = transitions.toString
    }


    val computeButton = input(`type` := "submit", value := "Calculate").render

    computeButton.onclick = (e: dom.Event) => update()

    target.innerHTML = ""

    target.appendChild(
        div(
            div(span(`class` := "query")("Total time in epoch: "), tInp),
            div(span(`class` := "query")("Starting population: "), initPopInp),
            div(span(`class` := "query")("Final population: "), finalPopInp),
            div(span(`class` := "query")("Average lifetime of a species: "), lifespanInp),
            div(p(" ")),
            div(computeButton),
            div(p(" ")),
            div(span(`class` := "result")("Number of extinctions: "), extOut),
            div(span(`class` := "result")("Number of transitions: "), transOut)
            ).render)

    def update() = {
      setExt()
      setTrans()
    }





  }

}
