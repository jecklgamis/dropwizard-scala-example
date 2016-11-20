package dropwizard.scala.example

import java.lang.annotation.Annotation

import com.google.inject.{Guice, Injector, Module, TypeLiteral}

import scala.collection.JavaConversions._
import scala.reflect._
import scala.language.implicitConversions

object GuiceInjector {

  def withInjector(modules: Module*)(fn: (Injector) => Unit) = {
    val injector = Guice.createInjector(modules.toList: _*)
    fn(injector)
  }

  implicit def wrap(injector: Injector): InjectorWrapper = new InjectorWrapper(injector)
}

class InjectorWrapper(injector: Injector) {
  def instancesWithAnnotation[T <: Annotation](annotationClass: Class[T]): List[AnyRef] = {
    injector.getAllBindings.filter { case (k, v) =>
      !k.getTypeLiteral.getRawType.getAnnotationsByType[T](annotationClass).isEmpty
    }.map { case (k, v) => injector.getInstance(k).asInstanceOf[AnyRef] }.toList
  }

  def instancesOfType[T: ClassTag](typeClass: Class[T]): List[T] = {
    injector.findBindingsByType(TypeLiteral.get(classTag[T].runtimeClass)).map { b =>
      injector.getInstance(b.getKey).asInstanceOf[T]
    }.toList
  }

  def dumpBindings(): Unit = {
    injector.getBindings.keySet() foreach { k =>
      println(s"bind key = ${k.toString}")
    }
  }
}