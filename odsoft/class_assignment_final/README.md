# Class assignment final - CMS Students

The current project took a two parts deliveries, where on the first delivery we had to build a pipeline with the following requirements for stages:

## Introduction
### Delivery one
`Pipeline Stages`

- **1** Repository Checkout Checkout the GIT repository;
- **2** War file Build and Publish the deployment file on Jenkins (e.g. for CMS: .war ) ;
- **3** Javadoc Generate and Publish the Javadoc on Jenkins;
- **4** Unit Tests Execution Execute the Unit Tests;
- **5** Unit Tests Report Generation and Publishing Generate and Publish the Unit Tests Report on Jenkins;
- **6** Unit Tests Report Coverage Report Generation and Publishing Generate and Publish the Unit Tests Coverage Report on Jenkins;
- **7** Integration Tests Execution Execute the Integration Tests;
- **8** Integration Tests Report Generation and Publishing Generate and Publish the Integration Tests Report on Jenkins;
- **9** Integration Tests Coverage Report Generation and Publishing Generate and Publish the Integration Tests Coverage Report on Jenkins;
- **13** Mutation Tests Execution Execute the Mutation Tests;
- **14** Mutation Tests Coverage Report Generation and Publishing Generate and Publish the Mutation Coverage Report on Jenkins;
- **15** System Test Deploy the application (e.g. .war file) to a pre-configured production server (e.g. Tomcat Server instance). Perform an automatic smoke test. This smoke test can be as simple as using curl to check if the base url of the application is responsive after staging deployment (e.g. on the Tomcat Server), ensuring that the application is properly deployed to the Staging Environment;
- **16** UI Acceptance Manual Tests A user should be notified by email of the successful execution of all the previous tests and be asked to perform a manual test. In order to cancel the progression or proceed, a UI Acceptance Manual Test must take place. The pipeline should wait for a user manual confirmation on Jenkins;
- **17** Continuous Integration Feedback Push a tag to the repository with the Jenkins build number and status (e.g. Build#32-Passed or Build#32-Failed).

For the propose of this class assignment some of the above stages were merged together. More details on the corresponding section.

It was also requested that the script would be compatible with both Unix and Windows systems.


### Delivery two

As for the second delivery it was necessary to keep the previous stages and enhance then while also given the project some additional features.
The additional features consist on the rework of the domain model of the project as well as the addition of the following business rules:
- a Book cannot be leased to different contacts at the same time
- Contacts cannot lease more than three books at a time
- Tags with the same description should not be allowed.

The image below illustrates the final result asked for the domain model:
![domain_model](./images/domain_model.png)

Another relevant component of the delivery two was the improvement of the pipeline. It consisted on adding features to the pipeline that were connected to the elements of the group. In this project the concerns addressed were:
- 2.1 - Base Pipeline
- 2.2 - Documentation and Database
- 2.3 - Integration Tests and Code Quality

For more details regarding the requirements for the above points, see the document odsoft_project_assignment_v1.2.pdf available on moodle.

### Unix/Windows systems compatible
To achieve compatible between Unix and Windows operating systems, we have developed the following functions:

```Groovy
def command(command) {
    if (isUnix() && command != null) {
        sh command
    } else {
        bat command
    }
}

def directory(directory) {
  newDir=directory
  if (!isUnix()) {
    newDir = newDir.replace('/','\\')
  }
  return newDir
}
```
By checking the global function **isUnix** we can determine what action to take.
## Pipeline design

## Git module used
Considering the size of our team and the professional experience of the elements of the group, we decided to use Trunk-based development (TBD).
This resulted on small merges being done from branches that implementation/added some new feature or fixed a bug. This approach also made sure that, we always had a working version of the master, since every branch was created from the master branch.

## Maturity level
