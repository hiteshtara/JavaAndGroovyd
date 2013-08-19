class Car2 implements GroovyInterceptable {
def check() { System.out.println "check called..." }
def start() { System.out.println "start called..." }
def drive() { System.out.println "drive called..." }
def invokeMethod(String name, args) 
{ 
	System.out.print("Call to $name intercepted... ")
if (name != 'check') {
System.out.print("running filter... ") 
Car2.metaClass.getMetaMethod('check').invoke(this, null)
} 
def validMethod = Car2.metaClass.getMetaMethod(name, args)
if (validMethod != null) 
	
	validMethod.invoke(this, args)
else {
Car2.metaClass.invokeMethod(this, name, args)
}}}
car=new Car2()
car.start()
car.drive()
 car.check()
 try {

 
car.speed()
} catch(Exception ex)
 { println ex
}
