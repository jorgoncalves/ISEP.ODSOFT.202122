# odsoft-edom-2017

The goal of this repository is to serve as a template for the exercises and projects developed during the ODSOFT course of the MEI Master Program, edition 2018/19.

** Please follow the instructions of the teachers of your course because they will always prevail over what is stated in this readme file. **

## 1. Structure of Folders

### 1.1 Project
You should use this folder for the development of class assignments and the project.
The project will use a Web Application developed with GWT. Later on, teachers will provide an initial version of this application (in the form of an Eclipse project) that you should copy into this folder.

### 1.2 ODSOFT
In this folder you should add **all** artifacts developed in the exercises of the ODSOFT course.

You should create a subfolder for each exercise/solution. For instance, subfolder "exercise1/component1" will contain all the artifacts for component 1 of exercise 1 of ODSOFT; subfolder "exercise1/component2" will contain all the artifacts for component 2 of exercise 1 of ODSOFT, etc.

**Note:** If for some reason you need to bypass these guidelines please ask for directions with your teacher and **always** state the exceptions in your commits and issues in bitbucket.

## 2. Issues and Commits

Each team will have a fork of this repository in bitbucket.

It is a good practice to create an **Issue** in bitbucket for each exercise or task that your team will be developing.

Since each exercise has multiple componentes you should create one issue for each exercise component.

For the title/name of the issue you should use the name of the course followed by the exercise number and by the component number.

For instance, each ODSOFT's class assignment of ODOSFT you should several issues: "ODSOFT - Class Assignment 1 - Component 1", "ODSOFT - Class Assignment 2 - Component 2", etc. In the description of each issue you should briefly describe what is entended with this component.

Each commit in your repository should always make a reference to the corresponding issue. For instance, in the previous example, if the issue created has an id 22, then in the commit message you should make a ref to that issue. Example: "(ODSOFT) Added class diagram for exercise 1, ref #22". You should also state the short name of the course.

You should also close the issue when the exercise or task is finished.

For each deadline/submission of your task/exercise/project you should also create a tag in the repository.

It is mandatory that you produce documentation for all your tasks. The documentaion **must** always be produced in the folder's readme.md files!

These readme.md files use markdown. You may find further documentation about markdown in [Markdown](https://en.wikipedia.org/wiki/Markdown)


## 3. How to Work with Eclipse and the Repository

When developing exercises or the project you may have to create new eclipse projects.

You can (and should) create the projects inside the repository so that that are shared with the team.

Take into account that Eclipse has the concept of Workspace. An workspace is where you have "links" to projects. A workspace is also a folder in the file system. **Workspaces should not be created inside the folder that contains your clone of the repository!** Workspaces should be local to your development pc (outside of the repository).

Suppose you have created the clone of the repository in "c:\isep\odsoft-edom-2017-g999". You could, for instance, create the workspace in "c:\isep\eclipse-workspace".

In order to open an existing project in Eclipse you should:

* Create a new folder for a new workspace or use an existing one.
* Run Eclipse
* Select the folder of the workspace if Eclipse ask for one when starting or select/change workspaces by using "File/Switch Workspace..."
* Import the project you want to open (it may be inside of the repository) into the workspace. You should use "File"/"Import..." and then "Existing Projects into Workspace". Then select the folder of the project that you want to import.
* Note: if the project is inside the repository **do not** check the option "Copy projects into workspace". That way you will work with the code that is inside the repository.

## 4. The ".gitignore" File

The root of this repository contains an "hidden" file named ".gitignore". This is a special file that Git uses to know about files that we do not want for Git to track. You should use it and update it as needed.
