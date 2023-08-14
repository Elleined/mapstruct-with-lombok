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
  - ***_**componentModel**_***: is used when using spring boot to make the mapper class avaialable in spring container.
  ```
    @Mapper(componentModel="spring")
  ```
  - ***_**uses**_***: is used when you have another mapper class that is needed when mapping. mapstruct will automatically detect it when you specify it as source.
  ```
    @Mapper(componentModel="spring", uses={OtherMapper.class})
  ```
  - ***_**imports**_***: is used when you have imports you need for your mappings. can be anything.
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
    public abstract Target foo(Source source, @Context SomeObject someObject);
  ```

### `@BeforeMapping`
- Used to execute code before the mapping method.
  ```
      //  If you have mapping method like this.
      public abstract Target foo(Source source);

      // The signature of method annotated with @BeforeMapping must be
      @BeforeMapping
      protected void bar(Source source) { // Execute some code before the mapping happens}

      // Basically the method parameters must be the same.
      // The return can be the target instead of void.
  ```  
  
### `@AfterMapping`
- Use to execute code after the mapping method. // Works almost the same as @BeforeMapping

### `@MappingTarget`
- Typically use in @BeforeMapping and @AfterMapping to get more flexibility allowing you to have reference in Target and Source object.
  ```
    @BeforeMapping or @AfterMapping
    protected void bar(Source source, @MappingTarget Target target) { // Execute some code before the mapping happens }
  ```  
  
# Lombok annotations
`@Data`
`@AllArgsConstructors`
`@NoArgsConstructor`
`@RequiredArgsConstructor`
`@Value`
`@Builder`
`@ToString`
`@EqualsAndHashCode`
`@Setter`
`@Getter`
