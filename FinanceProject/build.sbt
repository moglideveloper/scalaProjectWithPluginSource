name := "finance"

/*Our plugins is tightly dependent on scala-parallel-collections
which is only available with scala 2.13 onwards.
So, we are bound to use scalaVersion := "2.13.x"
 */
// https://mvnrepository.com/artifact/org.scala-lang.modules/scala-parallel-collections
libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.2"

scalaVersion := "2.13.5"


/*
Now, there are few libraries which are used
  * in this project (FinanceProject),
  * in SbtPluginWithComplexLogic plugin on which FinanceProject project depends on
  * in FinanceProjectDependency project on which FinanceProject project depends on

Let's take better files as an example
 */

libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.9.1"

val financeDependencySource = ProjectRef(uri("../FinanceProjectDependency"), "financeDependency")

val complexSbtPluginSource = ProjectRef(uri("../SbtPluginWithComplexLogic"), "complexSbtPlugin")

lazy val sampleProject = (project in file("."))
  .dependsOn(complexSbtPluginSource)
  .dependsOn(financeDependencySource)




