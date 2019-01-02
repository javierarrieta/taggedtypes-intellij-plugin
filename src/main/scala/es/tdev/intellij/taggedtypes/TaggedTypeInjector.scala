package es.tdev.intellij.taggedtypes

import org.jetbrains.plugins.scala.lang.psi.api.expr.ScAnnotation
import org.jetbrains.plugins.scala.lang.psi.api.toplevel.typedef.{ScTrait, ScTypeDefinition}
import org.jetbrains.plugins.scala.lang.psi.impl.toplevel.typedef.SyntheticMembersInjector

import scala.collection.immutable.Seq

class TaggedTypeInjector extends SyntheticMembersInjector {
  private final val annotationClassName = "es.tdev.taggedtypes.annotations.TaggedType"

  def createTaggedType(scTrait: ScTrait, scAnnotation: ScAnnotation): Seq[String] = Nil

  override def injectSupers(source: ScTypeDefinition): Seq[String] = {
    source match {
      case scTrait: ScTrait => Option(scTrait.findAnnotation(annotationClassName))
        .collect { case scAnnotation: ScAnnotation => println(s"Found annotation $annotationClassName, instrumenting the code for IDEA"); createTaggedType(scTrait, scAnnotation) }
        .getOrElse { println(s"Trait does not have the $annotationClassName, ignoring"); Nil }
      case _ => println(s"Trait does not have the $annotationClassName, ignoring"); Nil
    }
  }
}