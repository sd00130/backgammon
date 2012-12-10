After you imported Backgammon and jetty8.1.3-node-1 to your workspace

Go to Debug/Run Configurations and add new Java application (project: jetty8.1.3-node-1, Main class: org.eclipse.jetty.start.Main)

This is jetty webserver that runs as java application refering to Backgammon (jetty8.1.3-node-1/contexts/backgammon.xml)

Run jetty and open two browsers, go to http://localhost:8080/Backgammon/backgammon.html in both

Enter player names, click join, when you click join websocket connection is established

Now you can move chips across the field, click on chip then to projector (below the chip)

This is game prototype, contains some terracotta config however this bundle is not terracotta version.


