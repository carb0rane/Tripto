package com.apocalypse.tripto.ui.group

//data class GroupDataItem(
//    val _id: String?,
//    val date: String?,
//    val desc: String?,
//    val location: String?,
//    val userIds: List<String>,
//    val groupId:String
//
//)



data class GroupDataItem(
    val __v: Int,
    val _id: String,
    val comments: List<Any>,
    val createdAt: String,
    val date: String,
    val desc: String,
    val location: String,
    val updatedAt: String,
    val userIds: List<UserId>
)

data class UserId(
    val __v: Int,
    val _id: String,
    val city: String,
    val createdAt: String,
    val desc: String,
    val email: String,
    val gender: String,
    val mobile: String,
    val name: String,
    val profile_pic: String,
    val updatedAt: String,
    val username: String
)
// get group name
//get grp chat
data class GroupAdd(
    val userIds: String,
    val groupId:String
)
data class NewGroup(
    val userIds: String,
    val desc:String?,
    val location:String?,
    val date:String,
    val comments:Comment?
)
data class ResponseAfterGroupCreated(
    val status:String?
)
data class Comment(
    val userName:String,
    val userId:String,
    val comment:String
)