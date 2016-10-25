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
        <li> ${answer_list} </li>
        <#list not_answer_list as notanswer>
            <li> ${notanswer} </li>
        </#list>
        <form class="form-inline" method="POST" action="/part-of-speech/answer-check">
            <div class="form-group">
                <label for="user_input_answer">Answer</label>
                <input type="text"
                       class="form-control"
                       id="user_input_answer"
                       name="user_input_answer"
                       placeholder="Your Answer">
                <input name="prompt" id="prompt" type="hidden" value=${prompt}/>
                <input name="answer_list" id="answer_list" type="hidden" value=${answer_list}/>
            </div>
            <button type="submit" class="btn btn-default">Submit Answer</button>
        </form>
    </body>
</html>