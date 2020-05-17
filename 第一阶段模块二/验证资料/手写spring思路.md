
<h2 id="实现思路"><a name="t1"></a><a name="t1"></a>实现思路</h2>

<ol>
<li>读取XML文件</li>
<li>对指定的package 进行扫描(scan) ,找到那些标记为@Component的类，创建BeanDefinition <br>
 2.1  把一个package下面的class变成resource <br>
 2.2 使用ASM读取Resource中的注解 <br>
 2.3  创建BeanDefinition  </li>
<li>通过BeanDefinition创建Bean实例，根据注解来注入。</li>
</ol>

# 实现Scanner（对ASM的抽象）

![image](9C89A2993C1148EDA8D4F257CD7CCF56)

如类图所示，我们抽象了一个MetadataReader接口,该接口依赖于AnnotationVisitor抽象类和ClassVisitor抽象类，而AnnotationAttributesReadingVisitor类和ClassMetadataReadingVisitor类又是这两个抽象类的实现。由此我们就完全隐藏了AnnotationAttributesReadingVisitor类和ClassMetadataReadingVisitor类

![image](6AF84ED12EB74BEC971CA0F8CF8577E3)
用ScannedGenericBeanDefinition 专门用来表达通过Scann扫描出的BeanDefiniton。

## 上面我们实现了bean的生成这次实现依赖注入

具体实现
数据结构
![image](53D27B6E6A9C459684E84066E58A09E0)

DependencyDescriptor 类是用来描述依赖的。相关类图如下：

![image](9C10242926ED4D77829BB52750998284)

Bean的生命周期
Bean的生命周期共有四个阶段：
![image](43686AE8319D420A8EB11C64FC25C9A4)
在此处我们只关注Bean的实例化和初始化阶段。
![image](DD596DF9B4A1436DA99229028F56687C)