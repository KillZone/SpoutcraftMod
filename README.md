[![Spoutcraft](https://dl.dropboxusercontent.com/u/37060654/Images/Spoutcraft/spoutcraft.png)](https://github.com/Spoutcraft)
===========
Spoutcraft is a mod for Forge that has the ability to add more features to Minecraft through an easy-to-use implementation-free API.

## Team
[![Zidane](https://secure.gravatar.com/avatar/3b8d6171c3f15daf35328a4f04c83de9?s=48)](https://github.com/Zidane "Zidane, Lead Developer")
[![Grinch](https://secure.gravatar.com/avatar/19d97d07c8797464aa8b7e2e0481da78?s=48)](https://github.com/Grinch "Grinch, Developer")
[![Dockter](https://secure.gravatar.com/avatar/532e7ce3830bfb47b22c241d45e63cc9?s=48)](https://github.com/mcsnetworks "Dockter, Developer")

## Clone
If you are using Git, use this command to clone the project: `git clone git@github.com:AlmuraDev/SpoutcraftMod.git`

## Setup
__Note:__ If you do not have [Gradle](http://www.gradle.org) installed you can use the gradlew files included with the project in place of 'gradle' in the following command(s). If you are using Git Bash, Unix or OS X then use './gradlew'. If you are using Windows then use 'gradlew.bat'.

__For [Eclipse](http://www.eclipse.org)__<br>
1. Run `gradle setupDevWorkspace eclipse`<br>
2. Import SpoutcraftMod as an existing project inside Eclipse.<br>

__For [IntelliJ](http://www.jetbrains.com/idea/)__<br>
1. Run `gradle setupDevWorkspace ideaModule`<br>
2. Import SpoutcraftMod as a module inside IntelliJ.<br>

## Run
__Note 1:__ The following is aimed to help you setup run configurations for Eclipse and IntelliJ, if you do not want to be able to run SpoutcraftMod directly from your IDE then you can skip this.<br>
__Note 2:__ The working directories for both Client and Server will need to be created manually in the root of SpoutcraftMod's directory. Otherwise you'll get a few errors and will not be able to run it this way.<br>
__Note 3:__ When running the Server, make sure you set it to __online-mode=false__ in the server.properties in ~/run/server!

__For [Eclipse](http://www.eclipse.org)__<br>
1. Go to 'Run > Run Configurations'<br>
2. Right-click 'Java Application' and select 'New'<br>
3. Set the current project.<br>
4. Set the name as "Client" and apply the information for Client below.<br>
5. Repeat step 1 through 4, then set the name as "Server" and apply the information for Server below.<br>

__For [IntelliJ](http://www.jetbrains.com/idea/)__<br>
1. Go to 'Run > Edit Configurations'<br>
2. Click the green + button and select 'Application'<br>
3. Set the name as "Client" and apply the information for Client below.<br>
4. Repeat step 2 and set the name as "Server" and apply the information for Server below.<br>
 
__Client__
```
Main class: net.minecraft.launchwrapper.Launch
VM options: -Xmx1G -Xms512M -Dfml.ignoreInvalidMinecraftCertificates=true -Djava.library.path="../../build/natives"
Program arguments: --version 1.6 --tweakClass cpw.mods.fml.common.launcher.FMLTweaker --username Username
Working directory: Path/to/SpoutcraftMod/run/client
Use classpath of module: SpoutcraftMod (IntelliJ-only)
```

__Server__
```
Main class: cpw.mods.fml.relauncher.ServerLaunchWrapper
VM options: -Xmx1G -Xms512M -Dfml.ignoreInvalidMinecraftCertificates=true
Working directory: Path/to/SpoutcraftMod/run/server
Use classpath of module: SpoutcraftMod (IntelliJ-only)
```

## Build
__Note:__ If you do not have [Gradle](http://www.gradle.org) installed you can use the gradlew files included with the project in place of 'gradle' in the following command(s). If you are using Git Bash, Unix or OS X then use './gradlew'. If you are using Windows then use 'gradlew.bat'.

Run `gradle build`

This will produce a compiled JAR file for SpoutcraftMod in `SpoutcraftMod/build/libs` that includes the dependencies needed for it to run.

## Contributing
We're always happy to have people contribute to the project. Just make sure you follow these guidelines!

* Formatting Pull Requests will not be merged!
* Be quick and to the point with your Pull Request title.
* Be detailed about what you added/changed/removed and why you did so.
* Run `gradle licenseFormatMain` to apply license headers to new class files. Pull Requests with missing headers will not be merged.
* The code must be your work or you must accredit those whom you've taken code from appropriately.


## FAQ
__Why do I get `javac: source release 1.7 requires target release 1.7` in IntelliJ when running the client configuration?__
>Sometimes another project can mess with the settings in IntelliJ. Fixing this is relatively easy.

>1. Go to 'File > Settings'<br>
>2. Click the dropdown for 'Compiler' on the left-hand side and select 'Java Compiler'.<br>
>3. Select SpoutcraftMod and set the 'Target bytecode version' as '1.7'.<br>
>4. Click Apply and OK and try running it again.<br>

__A dependency was added but my IDE is missing it! How do I add it?__
>If a new dependency was added, you can run either 'gradle ideaModule' or for Eclipse 'gradle eclipse'. This should recreate the settings for your IDE and add the missing dependency.
