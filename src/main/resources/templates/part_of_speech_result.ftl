<html>
    <head>
        <title>Said</title>
    </head>
    <body>
        <h1> Your name is ${user_input_text}</h1>
        <br>
        <h2>Part of Speech Tagger</h2>
        <h2> </h2>
        <ul>
             <li> Token : Tag : Part of Speech</li>
          </ul>
        <#list pos_list as pos_dict>
          <ul>
             <li> ${pos_dict.token} , ${pos_dict.tag} , ${pos_dict.pos}</li>
          </ul>
        </#list>
    </body>
</html>