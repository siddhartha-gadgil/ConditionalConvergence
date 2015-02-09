package convergence

import org.scalajs.dom
import dom.html
import scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

@JSExport
object ConditionalConvergence{
  @JSExport
  def main(target: html.Div) = {
    target.appendChild(div(p("divs are nice")).render)
  }

}
