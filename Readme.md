# PI Uploader

## Table of contents
* [Overview](#overview)
* [Technologies](#technologies)
* [Functionalities](#functionalities)
* [Configuration](#configuration)

## Overview
Purpose of the project is to create a tool which allows update whole or part of application on Raspberry Pi.
The application can be uploaded currently only with using ftp or scp with bunch of commands, this tool has intended 
simplify this process.

## Technologies
Pi Uploader is prepared with Java 10 and Spring Boot. The Graphical UI is prepared in Java Swing technology.

## Functionalities
PiUploader can be build as a separate executable jar with external config.json
The application allows to:
* copy file[s] from a local folder to a remote directory (without nested folders or files)

## Configuration
Configuration is possible by `config.json` file which should be placed in `config` directory at the same location as an executable jar.
A config.json should contain:
```json
{
  "<name>": {
    "source": "<path_to_local_directory>",
    "destination": "<path_to_remote_directory>",
    "file": "<OPTIONAL: file_to_copy>"
  },
  "<name2>": {
    "source": "<path_to_local_directory>",
    "destination": "<path_to_remote_directory>",
    "file": "<OPTIONAL: file_to_copy>"
  },  
}
```
Where:
* name - is a text which will be visible on a selectable list on the UI
* source - defines an absolute path to the local directory where file[s] will be copied
* destination - defines an absolute path to the remote destination directory
* file - optional parameter, defines file to be copied from source to destination directory, when empty all files 
  from source folder will be copied

Example:
```json
{
  "start_apps": {
    "source": "E:\\Projects\\HomeActiveManager\\home-active-manager-parent\\release\\",
    "destination": "/usr/local/bin/test/homeActiveManager/",
    "file": "start_apps.sh"
  },
  "weather-service-module": {
    "source": "E:\\Projects\\HomeActiveManager\\home-active-manager-parent\\release\\weather-service-module\\",
    "destination": "/usr/local/bin/test/homeActiveManager/weather-service-module/"
  },
  "weather_app_jar": {
    "source": "E:\\Projects\\HomeActiveManager\\home-active-manager-parent\\release\\weather-service-module\\",
    "file": "weather-service-module-1.0-SNAPSHOT.jar",
    "destination": "/usr/local/bin/test/homeActiveManager/weather-service-module/"
  }
}
```