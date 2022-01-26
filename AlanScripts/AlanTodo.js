// Use this sample to create your own voice commands
intent('hello world', p => {
    p.play('(hello|hi there)');
});

intent('Say hurrah', p => {
    p.play('Hurrah!');
});

intent('What can you do?', p => {
    p.play('I can add a task and read them for you');
});

intent('(Go|take me) back', p => {
    p.play('Sure');
    p.play({ commandName: "go_back" });
});

intent('Add a todo task (named|with title|) $(TASK_TITLE* (.+))', p => {
    p.play('Adding the task');
    p.play({ commandName: "add_todo", title: p.TASK_TITLE.value });
});

projectAPI.greetUser = function(p, param, callback) {
    p.userData.nameOfUser = param.userName;
    p.play(`Hey ${p.userData.nameOfUser}, this is Alan. The voice assistant for Todo app. Here, you can store your daily tasks and prioritize them.`);
};