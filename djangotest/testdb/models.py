import uuid

from django.db import models
from django.utils.translation import gettext_lazy


class Subjects(models.Model):
    subject_id = models.UUIDField(primary_key=True, default=uuid.uuid4)

    title = models.CharField(max_length=30)

    def __str__(self):
        return self.title


class Groups(models.Model):
    group_id = models.UUIDField(
        primary_key=True,
        default=uuid.uuid4,
        verbose_name='Номер группы'
    )

    grade = models.IntegerField(editable=True)
    subject_id = models.ForeignKey(Subjects.subject_id,
                                   default=uuid.uuid4,
                                   on_delete=models.CASCADE,
                                   verbose_name='Номер учебного предмета')


class Users(models.Model):
    user_id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=True)
    name = models.CharField(max_length=30)
    surname = models.CharField(max_length=30)

    class Role(models.TextChoices):
        student = 'ST', gettext_lazy('Student')
        teacher = 'TC', gettext_lazy('Teacher')
        manager = 'MG', gettext_lazy('Manager')

    role = models.CharField(max_length=2, choices=Role.choices, default=Role.student)

    group_id = models.ForeignKey(
        Groups.group_id,
        primary_key=False,
        default=uuid.uuid4,
        on_delete=models.CASCADE,
        null=True
    )


class Themes(models.Model):
    theme_id = models.UUIDField(primary_key=True, verbose_name='Номер темы')

    subject_id = models.ForeignKey(Subjects.subject_id,
                                   default=uuid.uuid4,
                                   on_delete=models.CASCADE,
                                   verbose_name='Название учебного предмета')

    title = models.CharField(max_length=30, verbose_name='Название')

    def __str__(self):
        return self.title


class Tests(models.Model):
    test_id = models.UUIDField(primary_key=True)
    title = models.CharField(max_length=30)

    def __str__(self):
        return self.title


class Questions(models.Model):
    question_id = models.UUIDField(primary_key=True)
    theme_id = models.ForeignKey(Themes.theme_id, default=uuid.uuid4, on_delete=models.CASCADE)
    points = models.IntegerField(max_length=10)
    question_text = models.CharField(max_length=255, verbose_name='Текст вопроса')

    class Type(models.TextChoices):
        w_Image = 'IM', gettext_lazy('With Image')
        m_Choice = 'MC', gettext_lazy('Multiple Choice')
        s_Choice = 'SC', gettext_lazy('Single Choice')

    type = models.CharField(max_length=2, choices=Type.choices)

    test_id = models.ForeignKey(Tests.test_id, default=str, verbose_name='Номер теста', on_delete=models.CASCADE)


class Answers(models.Model):
    answer_id = models.UUIDField(primary_key=True)
    question_id = models.ForeignKey(Questions.question_id, default=uuid.uuid4, on_delete=models.CASCADE)
    is_right = models.BooleanField()
    answer_text = models.TextField(max_length=255)


class TestHistories(models.Model):
    test_histories_id = models.UUIDField(primary_key=True)
    user_id = models.ForeignKey(Users.user_id, default=str, on_delete=models.CASCADE)
    test_id = models.ForeignKey(Tests.test_id, default=int, on_delete=models.CASCADE)
    points = models.IntegerField(max_length=10)  # количество тестов в истории (?)
