package convergence

import org.scalajs.dom
import dom.html
import scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

@JSExport
object ConditionalConvergence{
  class BarBox(react : Int => Unit){
    val box = input(`type` :="number", placeholder := "input integer").render
    
    val bar = input(`type` := "range", min := 0, max := 100).render
    
    
  }
  
  /**
   * draws a line with default co-ordinates, implicity using rendering context.
   */
  def rawline(x1 : Int, y1: Int, x2: Int, y2: Int, 
      colour: String ="black", width: Int = 1)(
          implicit ctx: dom.CanvasRenderingContext2D) = {
    import ctx._
      beginPath()
      lineWidth = width
      strokeStyle = colour
      moveTo(x1,  y1)
      lineTo(x2, y2)
      closePath()
      stroke();
  }
  
  /**
   * A point.
   */
  case class Point(x : Int, y: Int){
    def lineTo(that : Point)(
          implicit 
          ctx: dom.CanvasRenderingContext2D, cnvs: html.Canvas) = line(this, that)
          
    def -->(that : Point)(
          implicit 
          ctx: dom.CanvasRenderingContext2D, cnvs: html.Canvas) = lineTo(that)
  }
  
  /**
   * draws line with origin at the centre of the canvas and increasing y upwards, 
   * with canvas picked up implicitly.
   */
  def line(start: Point, end: Point, colour: String ="black", width: Int = 1)(
          implicit ctx: dom.CanvasRenderingContext2D, cnvs: html.Canvas) = {
            val height = cnvs.height
            rawline(cnvs.width/2 + start.x, cnvs.height/2 - start.y, 
                cnvs.width/2 + end.x, cnvs.height/2 - end.y, colour, width)
          }
  
  def node(parent: html.Element)(offspring: html.Element*) = {
    parent.innerHTML = ""
    offspring.foreach(parent.appendChild(_))
  }
  
  @JSExport
  def main(target: html.Div) = {
    val height = 200
    val width = 600
    
    target.innerHTML = ""
    implicit val cnvs = canvas(style :="border:1px solid #00ffff;").render
    cnvs.height = height;
    cnvs.width = width;
    implicit val renderer = cnvs.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    val top = div(p("scala ", i("tags "), "are nice")).render
    val hello = div(p("Hello there")).render
    /*
    target.appendChild(div(p("scala ", i("tags "), "are nice")).render)
    target.appendChild(cnvs)
    target.appendChild(div(p("Hello")).render)*/
    renderer.fillStyle = "#f8f8f8"
    renderer.fillRect(0, 0, cnvs.width, cnvs.height)    
//    rawline(0, 0, 300, 100, "red", 3)
    
    val box = input(`type` :="number", placeholder := "height").render
    
    val bar = input(`type` := "range", min := 0, max := 100).render
    
    def yline = {
      val y = box.value.toInt
      Point(-width/4, y) --> Point (width/4, y)
    }
    
    target.onclick = (e: dom.Event) => yline
    
    box.onchange = (e: dom.Event) => {
      val y = box.value.toInt
      bar.value = box.value
      Point(-width/4, y) --> Point (width/4, y)
    }
    
    bar.oninput = (e : dom.Event) => {
      box.value = bar.value
    } 
    
    
    
    
    node(target)(top, cnvs, bar, box, hello)
    
 //   Point(0, 0) lineTo Point(100, 100)
    
    line(Point(-width/2, 0), Point(width/2, 0), "black", 2)
  }

}
