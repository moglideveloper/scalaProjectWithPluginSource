import sbt._, Keys._

object SamplePlugin extends AutoPlugin {


  object autoImport {
    lazy val custominfo = taskKey[Unit]("common info task")
  }

  import autoImport._



  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    custominfo := {
      println("this is a sample plugin that prints custom info")
    }

  )
}