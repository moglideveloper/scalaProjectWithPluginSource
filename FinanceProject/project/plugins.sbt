/*
if we use binary of sbt plugin, then everything works fine
 */
//addSbtPlugin("io.complex" % "sbt-plugin" % "1.0")

/*
Assume, this plugin is doing some complex cpu intensive task that have some bug.
Now to fix that bug, developer can follow following steps :-
1. addSbtPlugin("io.complex" % "sbt-plugin" % "1.0")
   will stay as it is in FinanceProject/project/plugins.sbt

2. attach source of "io.complex" % "sbt-plugin" % "1.0"

3. Start sbt in debug mode `sbt -jvm-debug 5005`

Above approach will work fine, bu every time, developer makes a change in
SamplePlugin, he/she has to build SbtPluginWithComplexDependency and reattach source
of Sample plugin in DebugYourPlugin project
 */
val pluginDirectoryPath = {
  val currentProjectDirectoryPath = sys.props("user.dir")
  val projectDirectoryParentPath = new File(currentProjectDirectoryPath).getParent
  val samplePluginDirectoryPath = projectDirectoryParentPath + "/SbtPluginWithComplexLogic"
  println(s"plugin directory path is >$samplePluginDirectoryPath<")
  samplePluginDirectoryPath
}

dependsOn( ProjectRef( new File( pluginDirectoryPath), "complexSbtPlugin" ) )