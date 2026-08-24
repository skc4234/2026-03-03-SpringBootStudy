const {defineStore} = Pinia
/*
	stompClient.send("/app/chat.private")
	       |
	@MessageMapping("/chat.private")
	       |
	privateMessage(ChatMessage message)
	       |
	"/queue/private/"+message.getReceiver()
	
	
	stompClient.send("/app/chat.send")
	       |
	@MessageMapping("/chat.send")
		   |
	sendMessage(ChatMessage message)
	       |
	return message
	       |
	@SendTo("/topic/public")
	       |
	  접속자 전체전송
	
*/
const useChatStore=defineStore('chat',{
	state:()=>({
		stompClient:null,
		userId:'',
		message:[],
		msg:'',
		receiver:''
	}),
	actions:{
		// 연결 후 데이터를 받는 브라우저 지정
		connect(){
			const socket=new SockJS('/ws-chat')
			this.stompClient=Stomp.over(socket)
			this.stompClient.connect({},()=>{
				this.stompClient.subscribe('/queue/private/'+this.userId,(msg)=>{
					this.message.push(JSON.parse(msg.body))
				})
			})
			console.log(this.message)
		},
		// 메시지 보내기("/app")
		send(){
			this.stompClient.send('/app/chat.private',{},JSON.stringify({
				sender: this.userId,
				receiver: this.receiver,
				message: this.msg
			}))
		}
	}
})