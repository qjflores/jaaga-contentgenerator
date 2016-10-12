<html>
    <head>
        <title>Said</title>
    </head>
    <body>
        <h1> Your name is ${user_input_text}</h1>
        <br>
        <h2>Part of Speech Tagger</h2>
        <h2> </h2>

        <#list tags as tag>
          <ul>
             <li>${tag}</li>
          </ul>
        </#list>
    </body>
</html>