package com.turk.dtos.serverObjects

data class FootballMatchResponse(val Team_A:String,
                                 val Team_B:String,
                                 val Score:String,
                                 val link_A:String,
                                 val link_B:String,
                                 val Date:String,
)

/*

"Team_A": "FC Barcelona",
"Team_B": "Chelsea",
"Score": "2-0",
"link_A": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU",
"link_B": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnVkgyvVxOrIGUfaoGPOQPbXKzQUKz7faW71gC7nnI_clFEPbQ81EDQ5T575enZ1Ea5PA&usqp=CAU",
"Date": "25 July 2021 21:00"*/
