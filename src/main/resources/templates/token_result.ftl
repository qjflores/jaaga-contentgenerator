<html>
    <head>
        <title>Said</title>
    </head>
    <body>
        <h1> Your name is ${user_input_text}</h1>
        <br>
        <h2>tokens</h2>
        <h2> </h2>

        <#list tokens as token>
          <ul>
             <li>${token}</li>
          </ul>
        </#list>
    </body>
</html>