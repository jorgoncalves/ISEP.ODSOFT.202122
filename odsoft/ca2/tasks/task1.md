Based on the criteria of installs we decided to go with Build Pipelines

- 32,788 installs Build Pipelines at 22/11/2021
- 12,267 installs Promoted Build at 22/11/2021

To start the implementation it is need to setup a free style project that is configure with the repository that is going to be used on the pipeline.

To setup this specific project it need to change the permissions for the `gradlew` file

```
cd odsoft/ca2/
chmod 777 ./gradlew
./gradlew clean build
```

to run the project

```
sudo ./gradlew gwtRun
```
