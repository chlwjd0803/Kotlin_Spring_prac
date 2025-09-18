package com.example.coconut_was.domains.project.entity

enum class BusinessType(private val description: String) {
    FOOD_BEVERAGE("식당/카페/주점"),
    RETAIL_COMMERCE("의류/쇼핑물"),
    BEAUTY_HEALTH("뷰티/헬스"),
    EDUCATION("교육/학원"),
    MEDICAL("병원/약국"),
    CULTURE_LEISURE("문화/여가"),
    PROFESSIONAL_SERVICE("서비스/전문직"),
    ACCOMMODATION("숙박/관광"),
    ETC("기타");
}