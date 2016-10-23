<html>
    <head>
        <title>Said</title>
    </head>
    <body>
    	<h1>Noun Problem</h1>
        <h1> Reading:${user_input_text}</h1>
        <br>
        <h1> ${prompt}</h1>
        <br>
		<#list answer_list as answer>
			<li> ${answer} </li>
		</#list>
		<#list not_answer_list as notanswer>
			<li> ${notanswer} </li>
		</#list>
    </body>
</html>