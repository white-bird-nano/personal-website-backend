package com.personal.website.model

data class Profile(
    val name: String,
    val nameEn: String,
    val title: String,
    val catchphrase: String,
    val bio: String,
    val avatarUrl: String,
    val social: Social,
    val skills: List<SkillCategory>
)

data class Social(
    val github: String?,
    val twitter: String?
)

data class SkillCategory(
    val category: String,
    val skills: List<String>
)
