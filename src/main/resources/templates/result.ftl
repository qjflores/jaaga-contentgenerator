<html>
    <head>
        <title>Said</title>
    </head>
    <body>
        <h1> Your name is ${user_input_text}</h1>
        <br>
        <h2>Sentences</h2>
        <h2> </h2>

        <#list sentences as sentence>
          <ul>
             <li>${sentence}</li>
          </ul>
        </#list>
        <h1>test</h1>
    </body>
</html>