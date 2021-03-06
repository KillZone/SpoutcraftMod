SpoutcraftMod
=============
[![Build Status](https://travis-ci.org/Spoutcraft/SpoutcraftMod.png?branch=master)](https://travis-ci.org/Spoutcraft/SpoutcraftMod) [![Coverage Status](https://coveralls.io/repos/Spoutcraft/SpoutcraftMod/badge.png)](https://coveralls.io/r/Spoutcraft/SpoutcraftMod)  
SpoutcraftMod is a Forge mod that expands Minecraft through an easy-to-use addon API. It is licensed under the [MIT License]

* [Homepage]
* [Source]
* [Issues]
* [Chat]: #spoutcraft on irc.esper.net

## Team
[![Zidane](https://secure.gravatar.com/avatar/3b8d6171c3f15daf35328a4f04c83de9?s=48)](https://github.com/Zidane "Zidane, Lead Developer")
[![Grinch](https://secure.gravatar.com/avatar/19d97d07c8797464aa8b7e2e0481da78?s=48)](https://github.com/Grinch "Grinch, Developer")
[![Dockter](https://secure.gravatar.com/avatar/532e7ce3830bfb47b22c241d45e63cc9?s=48)](https://github.com/mcsnetworks "Dockter, Developer")
[![unknownloner](https://secure.gravatar.com/avatar/4a8d2d1a1f594cacf05738f62d4c3a5c?s=48)](https://github.com/unknownloner "unknownloner, Developer")

## Cloning
If you are using Git, use this command to clone the project: `git clone git@github.com:Spoutcraft/SpoutcraftMod.git`

## Setup
__Note:__ If you do not have [Gradle] installed you can use the gradlew files included with the project in place of 'gradle' in the following command(s). If you are using Git Bash, Unix or OS X then use './gradlew'. If you are using Windows then use 'gradlew.bat'.

__For [Eclipse]__  
1. Run `gradle setupDecompWorkspace eclipse` (The decompile step may take a moment).  
2. Import SpoutcraftMod as an existing project inside Eclipse.

__For [IntelliJ]__  
1. Run `gradle setupDecompWorkspace ideaModule` (The decompile step may take a moment).  
2. Import SpoutcraftMod as a module inside IntelliJ.

## Running
__Note 1:__ The following is aimed to help you setup run configurations for Eclipse and IntelliJ, if you do not want to be able to run SpoutcraftMod directly from your IDE then you can skip this.  
__Note 2:__ The working directories for both Client and Server will need to be created manually in the root of SpoutcraftMod's directory. Otherwise you'll get a few errors and will not be able to run it this way.  
__Note 3:__ When running the Server, make sure you set it to *__online-mode=false__* in the server.properties in ~/run/server!  

__For [Eclipse]__  
1. Go to 'Run > Run Configurations'.  
2. Right-click 'Java Application' and select 'New'.  
3. Set the current project.  
4. Set the name as "Client" and apply the information for Client below.  
5. Repeat step 1 through 4, then set the name as "Server" and apply the information for Server below.  

__For [IntelliJ]__  
1. Go to 'Run > Edit Configurations'.  
2. Click the green + button and select 'Application'.  
3. Set the name as "Client" and apply the information for Client below.  
4. Repeat step 2 and set the name as "Server" and apply the information for Server below.  
 
__Client__
```
Main class:       net.minecraft.launchwrapper.Launch
VM options:       -Xmx1G -Xms512M -Dfml.ignoreInvalidMinecraftCertificates=true -Djava.library.path="../../build/natives"
Program args:     --version 1.6 --tweakClass cpw.mods.fml.common.launcher.FMLTweaker --username Username
Working dir:      Path/to/SpoutcraftMod/run/client
Module classpath: SpoutcraftMod (IntelliJ-only)
```

__Server__
```
Main class:       cpw.mods.fml.relauncher.ServerLaunchWrapper
VM options:       -Xmx1G -Xms512M -Dfml.ignoreInvalidMinecraftCertificates=true
Working dir:      Path/to/SpoutcraftMod/run/server
Module classpath: SpoutcraftMod (IntelliJ-only)
```

## Building
__Note:__ If you do not have [Gradle] installed you can use the gradlew files included with the project in place of 'gradle' in the following command(s). If you are using Git Bash, Unix or OS X then use './gradlew'. If you are using Windows then use 'gradlew.bat'.

In order to build SpoutcraftMod you simply need to run the `gradle` command. You can find the compiled JAR file in `~/build/distributions`.

## Contributing
Are you a talented programmer looking to contribute some code? We'd love the help!
* Open a pull request with your changes, following our [guidelines and coding standards](CONTRIBUTING.md).
* Please follow the above guidelines for your pull request(s) accepted.
* For help setting up the project, keep reading!

Love the project? Feel free to [donate] to help continue development! SpoutcraftMod is open-source and powered by community members, like yourself. Without you, we wouldn't be here today!

## FAQ
__Why do I get `javac: source release 1.7 requires target release 1.7` in IntelliJ when running the client configuration?__
>Sometimes another project can mess with the settings in IntelliJ. Fixing this is relatively easy.

>1. Go to 'File > Settings'.
>2. Click the drop down for 'Compiler' on the left-hand side and select 'Java Compiler'.
>3. Select SpoutcraftMod and set the 'Target bytecode version' as '1.7'.
>4. Click Apply and OK and try running it again.

__Why do I get `Zip file rt.jar failed to read properly` in IntelliJ?__
>This is the result of Forge attempting to classload the Java runtime JAR, overall it is not an error that will cause any harm to your development and should be ignored.

__A dependency was added, but my IDE is missing it! How do I add it?__
>If a new dependency was added, you can run either 'gradle ideaModule' or for Eclipse 'gradle eclipse'. This should recreate the settings for your IDE and add the missing dependency.

__Help! Things are not working!__
>Some issues can be resolved by deleting the '.gradle' folder in your user directory and running through the setup steps again. Otherwise if you are having trouble with something that the README does not cover, feel free to join our IRC channel and ask for assistance.

[Homepage]: http://spoutcraft.org/
[Forums]: http://spoutcraft.org/forums/
[Chat]: http://spoutcraft.org/chat/
[Issues]: http://spoutcraft.org/issues/
[Source]: https://github.com/Spoutcraft/SpoutcraftMod/
[Twitter]: https://twitter.com/Spoutcraft/
[Facebook]: http://www.facebook.com/pages/Spoutcraft/351909024946422/
[MIT License]: http://www.tldrlegal.com/license/mit-license/
[Gradle]: http://www.gradle.org/
[Eclipse]: http://www.eclipse.org/
[IntelliJ]: http://www.jetbrains.com/idea/