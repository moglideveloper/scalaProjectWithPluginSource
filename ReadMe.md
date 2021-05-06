<u><b>Issue Description</b></u>

There are total 3 sbt modules as described below :-   

FinanceProject -> This is main sbt project.

SbtPluginWithComplexLogic -> This is sbt plugin. FinanceProject depends on this sbt plugin   

FinanceProjectDependency -> This is a sbt project. FinanceProject depends on this project

---

FinanceProject is tightly dependent on
scala-parallel-collections.     

And scala-parallel-collections is only available with scala 2.13 onwards.   

So, scala version in FinanceProject build.sbt is 2.13.x :-   
`scalaVersion := "2.13.5"`

---

Assume, SbtPluginWithComplexLogic plugin is doing some complex cpu intensive task that have some bug.   

Now to fix that bug, developer can follow either of the two approaches :-

<u><b>1. Add Sbt Plugin Binary Sources</b></u>

* attach source of "io.complex" % "sbt-plugin" % "1.0" in FinanceProject

* Start sbt in debug mode `sbt -jvm-debug 5005`

Above approach will work fine, but every time, developer makes a change in
SbtPluginWithComplexLogic,   
he/she has to rebuild SbtPluginWithComplexLogic and   
re-attach source
of SbtPluginWithComplexLogic in FinanceProject.   

This approach is more time taking then the below discussed approach.


<u><b>2. Add Sbt Plugin Source as a dependency using dependsOn</b></u>

FinanceProject is using   
`scalaVersion := "2.13.5"`   
and   
SbtPluginWithComplexLogic is using scala version 2.12.x,   
since sbt and the sbt API are only compiled for 2.12.x

Reference :-   
https://github.com/sbt/sbt/issues/5360

https://github.com/portable-scala/sbt-crossproject/issues/123

---

<b><i>
Now, if there is any library that is common in project and sbt plugin,   
it is resolved with conflicting cross-version suffixes.   
</i></b>

For example, in attached project   
`"com.github.pathikrit" %% "better-files" % "3.9.1"`   
is used in sbt project and sbt plugin.  

Below is the error stack trace :-

```
[error] Modules were resolved with conflicting cross-version suffixes in ProjectRef(uri("file:/Users/dev/groundzero/quickPrototype/scalaProjectWithPluginSource/FinanceProject/"), "sampleProject"):
[error]    com.github.pathikrit:better-files _2.13, _2.12
[error] stack trace is suppressed; run 'last update' for the full output
[error] stack trace is suppressed; run 'last ssExtractDependencies' for the full output
[error] (update) Conflicting cross-version suffixes in: com.github.pathikrit:better-files
[error] (ssExtractDependencies) Conflicting cross-version suffixes in: com.github.pathikrit:better-files
```

How to resolve this error, considering we can't use scala version 2.12.x,   
as our project heavily depends on scala-parallel-collections (which is only available from scala 2.13.x onwards).



