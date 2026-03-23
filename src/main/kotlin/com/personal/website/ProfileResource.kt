package com.personal.website

import com.personal.website.model.Profile
import com.personal.website.model.SkillCategory
import com.personal.website.model.Social
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/api/v1")
class ProfileResource {

    @GET
    @Path("/profile")
    @Produces(MediaType.APPLICATION_JSON)
    fun getProfile(): Profile {
        return Profile(
            name = "山田 太郎",
            nameEn = "Taro Yamada",
            title = "Software Engineer",
            catchphrase = "コードで世界を少し良くする",
            bio = "バックエンドからクラウドまで幅広く開発しています。KotlinとGoogle Cloudが得意です。",
            avatarUrl = "",
            social = Social(
                github = "https://github.com/white-bird-nano",
                twitter = null
            ),
            skills = listOf(
                SkillCategory("Backend", listOf("Kotlin", "Java", "Spring Boot", "Quarkus", "Python")),
                SkillCategory("Frontend", listOf("TypeScript", "React", "Next.js", "Tailwind CSS")),
                SkillCategory("Infrastructure", listOf("Google Cloud", "Terraform", "Docker", "Kubernetes")),
                SkillCategory("Tools", listOf("Git", "GitHub Actions", "Obsidian"))
            )
        )
    }
}
