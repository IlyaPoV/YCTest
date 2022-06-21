from django.contrib import admin
from .models import Tests, Themes, Questions, TestHistories, Answers, Users, Groups, Subjects


class QuestionsAdmin(admin.ModelAdmin):
    list_display = ('question_id', 'theme_id', 'points', 'question_text', 'type', 'test_id')


class AnswersAdmin(admin.ModelAdmin):
    list_display = ('answer_id', 'question_id', 'answer_text', 'is_right')


class UsersAdmin(admin.ModelAdmin):
    list_display = ('user_id', 'name', 'surname', 'role', 'group_id')


class GroupsAdmin(admin.ModelAdmin):
    list_display = ('group_id', 'grade', 'subject_id')


class SubjectsAdmin(admin.ModelAdmin):
    list_display = ('subject_id', 'title')


class TestHistoriesAdmin(admin.ModelAdmin):
    list_display = ('test_histories_id', 'user_id', 'test_id', 'points')


class ThemesAdmin(admin.ModelAdmin):
    list_display = ('theme_id', 'title')


class TestsAdmin(admin.ModelAdmin):
    list_display = ('test_id', 'title')


admin.site.register(Questions, QuestionsAdmin)
admin.site.register(Answers, AnswersAdmin)
admin.site.register(Users, UsersAdmin)
admin.site.register(Groups, GroupsAdmin)
admin.site.register(Subjects, SubjectsAdmin)
admin.site.register(TestHistories, TestHistoriesAdmin)
admin.site.register(Themes, ThemesAdmin)
admin.site.register(Tests, TestsAdmin)
