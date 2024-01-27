# mapstruct-with-lombok
Creating data class mapper with Mapstruct and Lombok

# What is Mapstruct
  - MapStruct is used to both generate boilerplate code for model to dto and dto to model that is type safe and more concise with help of annotations.

# What is Lombok 
  - Lombok is used to generate generic boilerplate code for POJO classes. Like Getters, Setters, ToString, NoArgsConstructor, AllArgsConstructor, RequiredArgsConstructor, EqualsAndHashCode, and Builder patterm with the help of annotations that make POJO classes  error prone and readable.

# Mapstruct annotations
### `@Mapper`
- Used to mark the class as mapper class that will contain the mapping between dto to model and vice versa.  
  ##### `@Mapper attributes`
  - ***_**componentModel**_***: Used when using spring boot to make the mapper class avaialable in spring container.
  ```
    @Mapper(componentModel="spring")
  ```
  - ***_**uses**_***: Used when you need another mapper class in you mapping method.
  ```
    @Mapper(componentModel="spring", uses={OtherMapper.class})
  ```
  - ***_**imports**_***: Used when you have imports you need for your mappings. can be anything.
  ```
    @Mapper(componentModel="spring", uses={OtherMapper.class}, imports={SomeClassYouNeedAsImport.class})
  ```
  
### `@Mappings` 
- Used to act as container of @Mapping for readability.
```
  @Mappings({
    @Mapping(target=" ", source= " "),
    @Mapping(target=" ", source= " "),
  })
  public abstract Target foo(Source source);
```  

### `@Mapping`
- Used to map the fields appropriately. 
  ```
    @Mapping(target=" ", source= " ")
    public abstract Target foo(Source source);
  ```
  ##### `@Mapping attributes`
  - ***_**target**_***: This is the return type field.
  - ***_**source**_***: This is the parameter type field.
    ```
      @Mapping(target="targetFieldName", source="source.fieldName")
    ```
  - ***_**expression**_***: Inside this SpeL expression "java(//Execute some code)" will be executed. Typically use when you have complex attribute to map or you need a default value or you have @Context annotation used.
    ```
      // Basically anything you can execute anything as long the targetFieldName is the return type of the method you gonna execute.
      @Mapping(target="targetFieldName", expression="java(bar())")
    ```
  - ***_**ignore**_***: Used when you want to ignore targetFieldName when mapping.
    ```
      @Mapping(target="targetFieldName", ignore=true)
    ```

### `@Context`
- Used to add some context in mapping method. You can use many @Context annotation in one mapping method. You can use this if the mapping method requires additional information that source cannot contain.
  ```
    @Mapping(target="targetFieldName", expression="java(someObject)")
    public abstract Target foo(Source source, @Context SomeObject someObject);
  ```

### `@BeforeMapping`
- Used to execute code before the mapping method.
  ```
      //  If you have mapping method like this.
      public abstract Target foo(Source source);

      // The signature of method annotated with @BeforeMapping must be
      @BeforeMapping
      protected void bar(Source source) { // Execute some code before the mapping happens }

      // Basically the method parameters must be the same.
      // The return can also be the target instead of void.
  ```  
  
### `@AfterMapping`
- Use to execute code after the mapping method. // Works almost the same as @BeforeMapping

### `@MappingTarget`
- Typically use in @BeforeMapping and @AfterMapping to get more flexibility allowing you to have reference both in Target and Source object.
  ```
    @BeforeMapping or @AfterMapping
    protected void bar(Source source, @MappingTarget Target target) { // Execute some code before the mapping happens }
  ```  
  
# Lombok annotations
### `@Data`
- Use to generate getters, setters, toString, required args constructor, equals and hashCode.

### `@AllArgsConstructors`
- Use to generate all argument constructor.

### `@NoArgsConstructor`
- Used to generate no argument constructor.

### `@RequiredArgsConstructor`
- Use to generate a constructor for field that annotated with @NonNull and final variables.
  
### `@Value`
- Used to make the annotated class final and all the instance variable private and final.
  
### `@Builder`
- Use to generate builder pattern in annotated class.
  
### `@ToString`
- Used to generate toString. !Caution do not use this with entity class when working with spring data jpa.
  
### `@EqualsAndHashCode`
- Used to generate equals and hashcode. !Caution do not use this with entity class when working with spring data jpa.
  
### `@Setter`
- Usage scope: Class level, Field level
- Used to generate setters for all instance variable when use as class level and only generate for himself if used in field level.
- Field level annotation overrides Class level annotation.

### `@Getter`
- Usage scope: Class level, Field level
- Used to generate getters for all instance variable when use as class level and only generate for himself if used in field level.
- Field level annotation overrides Class level annotation.
  

# How to use MapStruct with Lombok
- Check the pom.xml file structure and always check for new versions.
- Check the code that almost covers the commonly use mapping when working with projects.
- I already do research for mapstruct and lombok to work together so copying the pom.xml structure should be fine and also works for you.

# How does mapstruct works behind the scene
- Mapstruct uses the annotations you write in mapper class to get the metadata and uses reflection api to create the implementation class on build time. Thats why you can specify in @Mapping the field that will be map together and also use source.fieldName in source attribute and the same as in target attribute targetFieldName.

# Where does the generated implementation of mapstruct resides.
- in you project structure target | generated-sources | annotation | groupid and artifactid folder | mapper

# Mapstruct plugin
- IntelliJ: https://plugins.jetbrains.com/plugin/10036-mapstruct-support

# Useful Links
- [Mapper can't call the @AfterNapping and @BeforeMapping annotated method](https://github.com/mapstruct/mapstruct/issues/2848)
