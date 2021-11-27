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

#### Note

gradlew permissions were updated

to run the project

```
sudo ./gradlew gwtRun
```

---

Test report
odsoft/ca2/docs/javadoc

---

The sequence can be created by on the job configuration defining:

- Build Triggers -> Build after other projects are built
- Post-build Actions -> Build other projects

---

---

## JobFanIn

---

Custom workspace
CMS_Repository_Part2_Task1

---

## Deploy to container plugin

---

---

## ATTENTION TO THE WORKSPACE

--
Groovy plugin installed

https://plugins.jenkins.io/htmlpublisher/
https://plugins.jenkins.io/seleniumhtmlreport/ x


---

SMTP configuration


---


---
Smoke test
curl -v --silent http://tomcat:8181/Part2_Task1/Showcase.html\#\!CwContacts --stderr - | if grep -q "Amelie Crutcher"
then
    echo "Amelia was found!"
else
	echo "Amelia wasn't found!"
    exit 1
fi

---

Talk about parametrized parameters