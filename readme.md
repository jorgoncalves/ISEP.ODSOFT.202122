# ODSOFT 2021-2022 ed.

The goal of this repository is to serve as a template for the exercises and projects developed during the ODSOFT course of the MEI Master Program, edition 2021/22.

** Please follow the instructions of the teachers of your course because they will always prevail over what is stated in this readme file. **

## 1. Structure of Folders

### 1.1 Project
You should use this folder for the development of class assignments and the project.

### 1.2 ODSOFT
In this folder you should add **all** artifacts developed in the exercises of the ODSOFT course.

You should create a subfolder for the each exercise/solution. For instance, subfolder "odsoft/ca1" shall contain all the artifacts for the class assignment part 1.

**Note:** If for some reason you need to bypass these guidelines please ask for directions with your teacher and **always** state the exceptions in your commits and issues in bitbucket.

## 2. Issues and Commits

Each team will have a fork of this repository in bitbucket.

It is a good practice to create an **Issue** in bitbucket for each exercise or task that your team will be developing.

Since each exercise has multiple goalds you should create one issue for each goal.

For the title/name of the issue you should use something that resembles the goal.

Each commit in your repository should always make a reference to the corresponding issue. For instance, in the previous example, if the issue created has an id 22, then in the commit message you should make a ref to that issue. Example: "ODSOFT: Added class diagram for exercise 1, ref #22". If you use the repository for more than one subject, you should also state the short name of the course.

You should also close the issue when the exercise or task is finished.

For each deadline/submission of your task/exercise/project you should also create a tag in the repository.

It is mandatory that you produce documentation for all your tasks. The documentaion **must** always be produced in the folder's readme.md files!

These readme.md files use markdown. You may find further documentation about markdown in [Markdown](https://en.wikipedia.org/wiki/Markdown)

## 3. The ".gitignore" File

The root of this repository contains an "hidden" file named ".gitignore". This is a special file that Git uses to know about files that we do not want for Git to track. You should use and update it as needed.