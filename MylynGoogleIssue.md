# Introduction #

Eclipse Mylyn'a google code issue repository'sini eklemek

Orjinal sayfası : http://code.google.com/p/jtpd/wiki/MylynGoogleIssue?ts=1249907422&updated=MylynGoogleIssue


# Details #
The instructions I previously posted to set up Mylyn to work with Google Code do not seem to work with Eclipse 3.4 (Ganymede). Luckily, readers of the previous post have kindly left comments with updated instructions on how to make this tool work again (many thanks to Christopher Barber, RoSt, Sergey Kolos and LI Daobing.)

Here we go:

  1. Update Mylyn to version 3.0.2.

  1. Go to the "Software Updates" dialog (menu "Menu" / "Software Updates...")
> 2. Once in the dialog, select the "Installed Software" tab and press the "Update..." button
> 3. Eclipse will look for updates of all the Eclipse plugins installed (warning: this is painfully slow)
> 4. Once the list of "Available Updates" is generated, select Mylyn, accept the license terms and press the "Finish" button
> 5. Restart Eclipse once the update process is complete
> 2. Download the Web Templates Connector for Mylyn.

  1. Go to the "Software Updates" dialog (menu "Menu" / "Software Updates...")
> 2. Once in the dialog, select the "Available Software" tab
> 3. Add a new site with the URL http://download.eclipse.org/tools/mylyn/update/incubator (Mylyn Incubator)
> 4. Select the connector Mylyn Connector: Web Templates (Advanced)
> 5. Press the "Install" button (warning: this is also painfully slow)
> 3. Open the "Task Repositories" View (menu "Window" / "Show View" / "Other...")


> 4. Click on the "Add Task Repository" button.
> 5. In the "Add Task Repository" wizard, select "Web Template (Advanced)" and click "Next."


> 6. Select "Eclipse Outliner (Google Code)" from the drop-down (this is the provided template.)


> 7. Change the server link and label (in my case I used http://code.google.com/p/fest and "FEST," respectively)
> 8. Change the query request URL to



> ${serverUrl}/csv?can=1&colspec=ID+Status+Type+Owner+Summary


> 9. Change the query pattern to



> "({Id}[0-9]+?)","({Status}.+?)","({Type}.+?)","({Owner}.+?)","({Description}.+?)"\s


  1. . Right-click the new task repository and create a new query.
  1. . Voilà! Now you can see all the tasks in your repository, including the completed ones!




Enjoy!