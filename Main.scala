package Main

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import scala.scalajs.js
import js.JSConverters.*
import scala.scalajs.js.annotation.*
import util.control.Exception.*
import com.raquo.laminar.nodes.ParentNode
import com.raquo.airstream.state.Var

object Element {
  @JSExportTopLevel("nameVar")
  val nameVar: Var[String] = {
    js.`import`.meta.hot.accept(() => {})
    Var(initial = "OwO")
  }

  @JSExportTopLevel("element")
  val element: HtmlElement = {
    val elNode = div(
      label("Your name: "),
      input(
        onMountFocus,
        placeholder := "Enter your name here",
        onInput.mapToValue --> nameVar,
        value <-- nameVar
      ),
      span(
        "Hello, ",
        child.text <-- nameVar.signal.map(_.toUpperCase)
      )
    )

    js.`import`.meta.hot.accept((newModule: js.Dynamic) => {
      val newElNode: HtmlElement =
        newModule.$t_LMain_Element$__element.asInstanceOf

      elNode.maybeParent.map(p =>
        ParentNode.replaceChild(
          p,
          elNode,
          newElNode
        )
      )

      val newNameVar: Var[String] =
        newModule.$t_LMain_Element$__nameVar.asInstanceOf

      newNameVar.set(
        allCatch
          .opt(js.`import`.meta.hot.data.namedVar.asInstanceOf[String])
          .getOrElse("123")
      )

    })

    js.`import`.meta.hot.dispose(() => {
      js.`import`.meta.hot.data.namedVar = nameVar.now()
    })

    elNode
  }
}

object Element2 {
  @JSExportTopLevel("nameVar2")
  val nameVar: Var[String] = {
    js.`import`.meta.hot.accept(() => {})
    Var(initial = "OwO")
  }

  @JSExportTopLevel("element2")
  val element: HtmlElement = {
    val elNode = div(
      label("Your name: "),
      input(
        onMountFocus,
        placeholder := "Enter your name here",
        onInput.mapToValue --> nameVar,
        value <-- nameVar
      ),
      span(
        "Hello, ",
        child.text <-- nameVar.signal.map(_.toUpperCase)
      )
    )

    elNode
  }
}

object App {
  @JSExportTopLevel("mount")
  def mount(): Unit = {
    val appContainer = dom.document.querySelector("#app")
    render(appContainer, div(Element.element, Element2.element))
  }
}
