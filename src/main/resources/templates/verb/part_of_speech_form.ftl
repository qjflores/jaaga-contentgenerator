<html>
    <head>
        <title>Enter Text to Process</title>
 
        <link href="css/bootstrap.css" rel="stylesheet" />
    </head>
    <body>
        <h2>Part of Speech Example</h2>
        <form class="form-inline" method="POST" action="/opennlp/part-of-speech/verb/result">
            <div class="form-group">
                <label for="user_input_text">Name</label>
                <input type="text"
                       class="form-control"
                       id="user_input_text"
                       name="user_input_text"
                       placeholder="John ddd">
            </div>
            <button type="submit" class="btn btn-default">Submit Text</button>
        </form>
    <body>
</html>