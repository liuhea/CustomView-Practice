## Dagger介绍
Dagger是一个完全静态的，在编译时进行依赖注入的框架，

我们在做项目时，经常需要在一个对象里去创建另一个对象的实例，这种行为是产生耦合的常见形式，对于一个大型项目来说，过多的相互依赖会导致代码难以维护，
很容易就会碰到修改一个小需求需要大面积的修改各种代码。

在需要依赖的类中不要通过new来创建依赖而是通过方法提供的参数注入进来，这样我们的需要依赖的类和提供依赖的类的实现方法分隔开了，一切又变得如此美好咯。

dagger2是一个依赖注入框架，在编译期间自动生成代码，负责依赖对象的创建。

用dagger2提供依赖有什么好处：为了进一步解耦和方便测试，我们会使用依赖注入的方式构建对象 (不使用 new 来创建依赖对象)。在mvp中，presenter层会持有view和model层的依赖，依赖注入主要用于解耦，通过依赖注入创建对象，不再使用new来创建对象。


## 常用注解

@Inject
这个注解是用来说明该注解下方的属性或方法需要依赖注入。（如果使用在类构造方法上，则该类也会被注册在DI容器中作为注入对象。很重要，理解这个，就能理解Presenter注入到Activity的步骤！）

@Provider
在@Module注解的类中，使用@Provider注解，说明提供依赖注入的具体对象

@Component
简单说就是，可以通过Component访问到Module中提供的依赖注入对象。假设，如果有两个Module，AModule、BModule，如果Component只注册了AModule，而没有注册BModule，那么BModule中提供的对象，无法进行依赖注入！

@SubComponent
该注解从名字上就能知道，它是子Component，会完全继承父Component的所有依赖注入对象！

@Singleton
被注解的对象，在App中是单例存在的！

@Scope
用来标注依赖注入对象的适用范围。

@Named(@)
因为Dagger2 的以来注入是使用类型推断的，所以同一类型的对象就无法区分，可以使用@Named注解区分同一类型对象，可以理解为对象的别名！

