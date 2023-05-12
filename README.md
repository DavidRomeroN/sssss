# POO2023-G1
# Programación Orientada a Objetos

[Tutorial de Programacion Java Swing](https://www.javatpoint.com/java-swing)

[Programación Java](https://drive.google.com/file/d/1TcAQVsUFGIG9C7QKWaFJ0VVpYHpcSpHf/view)
# Comandos clonar un repositorio en su PC
```console
d:
cd "DESARROLLO 2023"
dir
git clone -b <nombreBranch> https://github.com/davidmp/FundamentosP2022-G1.git
doskey /history
```
[Comandos para trabajar con ramas](https://docs.google.com/presentation/d/1T_p1gdUol1fm7hfMCzobEvEzWoFgvkTb/edit#slide=id.g22d070b325e_4_0)

# Comandos para subir a Github desde Repositorio local de PC o Plataformas Online

```console
    1  git status
    2  git add -A .
    3  git status
    4  git commit -a -m "Ejercicios condicionales"
    5  git status
    6  git pull
    7  git push
```

# Comandos Para actualizar cambios nuevos de Repositorio Remoto hacia Repositorio Local

```console
    1  git pull
```

# Comandos para compilar varias clases

```console
    javac -d ./ ./*.java
    javac -d . *.java
    
    java subprogramas.ClaseGeneral
```

# Crear Proyectos de Tipo Maven
```console
    mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart
```
# Pom.xml
```xml
	<properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
	</properties>
    
<build>
  <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${project.build.directory}/lib</outputDirectory>
                            <overWriteReleases>false</overWriteReleases>
                            <overWriteSnapshots>false</overWriteSnapshots>
                            <overWriteIfNewer>true</overWriteIfNewer>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
    
            <plugin>
              <!-- Build an executable JAR -->
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-jar-plugin</artifactId>
              <version>2.4</version>
              <configuration>
                <archive>
                  <manifest>
                    <addClasspath>true</addClasspath>
                    <classpathPrefix>lib/</classpathPrefix>   			  
                    <mainClass>pe.edu.upeu.app.App</mainClass>
                  </manifest>
                </archive>
              </configuration>
            </plugin>
  </plugins>
</build>    
    
```
