create database cms
go

use cms

go

create table UserTypes(
	id int primary key,
	[type] varchar(20)
)

create table Users(
	id int primary key identity(1, 1),
	username varchar(60) not null unique,
	email varchar(60) not null unique,
	[password] varchar(80) not null,
	firstName varchar(30),
	lastName varchar(30),
	dateOfBirth DATE,
	phoneNumber varchar(15),
	country varchar(30),
	city varchar(30),
	street varchar(30),
	streetNumber int,
	userTypeId int not null,
	constraint FK_Users_UserTypes foreign key (userTypeId) references UserTypes (id)
	on update cascade
	on delete NO ACTION
)

create table Chairs(
	id int not null,
	constraint PK_Chairs primary key (id),
	constraint FK_Chairs_Users foreign key (id) references Users (id)
	on update cascade
	on delete cascade
)

create table Authors(
	id int not null,
	constraint PK_Authors primary key (id),
	constraint FK_Authors_Users foreign key (id) references Users (id)
	on update cascade
	on delete cascade
)

create table Reviewers(
	id int not null,
	constraint PK_Reviewers primary key (id),
	constraint FK_Reviewers_Users foreign key (id) references Users (id)
	on update cascade
	on delete cascade
)

-------------------------------------------------------------------------

create table Organizers(
	id int primary key identity(1, 1),
	[name] varchar(50),
	email varchar(60),
	phoneNumber varchar(15),
)

create table Conferences(
	id int primary key identity(1, 1),
	[name] varchar(50) not null,
	URL varchar(100),
	chairId int not null,
	organizerId int not null,
	paperSubmissionDeadline DATETIME,
	paperRebiewDeadline DATETIME,
	acceptanceNotificationDeadline DATETIME,
	uploadingPaperDeadline DATETIME,
	constraint FK_Conferences_Chairs foreign key (chairId) references Chairs (id)
	on update cascade
	on delete NO ACTION,
	constraint FK_Conferences_Organizers foreign key (organizerId) references Organizers (id)
	on update cascade
	on delete NO ACTION
)


create table ConferenceSessions(
	id int primary key identity(1, 1),
	conferenceId int not null,
	constraint FK_ConferenceSessions_Conferences foreign key (conferenceId) references Conferences (id)
	on update cascade
	on delete cascade
)

create table TopicsOfInterest(
	id int primary key identity(1, 1),
	[description] varchar(50)
)

create table ConferencesTopicsOfInterest(
	conferenceId int not null,
	topicId int not null,
	constraint PK_ConferencesTopicsOfInterest primary key (conferenceId, topicId),
	constraint FK_ConferencesTopicsOfInterest_Conferences foreign key (conferenceId) references Conferences (id)
	on update cascade
	on delete cascade,
	constraint FK_ConferencesTopicsOfInterest_TopicsOfInterest foreign key (topicId) references TopicsOfInterest (id)
	on update cascade
	on delete cascade
)

create table ReviewersTopicsOfInterest(
	reviewerId int not null,
	topicId int not null,
	constraint PK_ReviewersTopicsOfInterest primary key (reviewerId, topicId),
	constraint FK_ReviewersTopicsOfInterest_Reviewers foreign key (reviewerId) references Reviewers (id)
	on update cascade
	on delete cascade,
	constraint FK_ReviewersTopicsOfInterest_TopicsOfInterest foreign key (topicId) references TopicsOfInterest (id)
	on update cascade
	on delete cascade
)


create table Papers(
	id int primary key identity(1, 1),
	title varchar(100) not null,
	abstract text,
	[status] varchar(15) not null,
	cameraReadyCopyURL varchar(100),
	fullPaperURL varchar(100),
)

create table PapersTopicsOfInterest(
	paperId int not null,
	topicId int not null,
	constraint PK_PapersTopicsOfInterest primary key (paperId, topicId),
	constraint FK_PapersTopicsOfInterest_Papers foreign key (paperId) references Papers (id)
	on update cascade
	on delete cascade,
	constraint FK_PapersTopicsOfInterest_TopicsOfInterest foreign key (topicId) references TopicsOfInterest (id)
	on update cascade
	on delete cascade
)

create table Keywords(
	id int primary key identity(1, 1),
	name varchar(50) not null,
)

create table PapersKeywords(
	paperId int not null,
	keywordId int not null,
	constraint PK_PapersKeywords primary key (paperId, keywordId),
	constraint FK_PapersKeywords_Papers foreign key (paperId) references Papers (id)
	on update cascade
	on delete cascade,
	constraint FK_PapersKeywords_Keywords foreign key (keywordId) references Keywords (id)
	on update cascade
	on delete cascade
)

create table AuthorsPapers(
	authorId int not null,
	paperId int not null,
	constraint PK_AuthorsPapers primary key (authorId, paperId),
	constraint FK_AuthorsPapers_Authors foreign key (authorId) references Authors (id)
	on update cascade
	on delete cascade,
	constraint FK_AuthorsPapers_Papers foreign key (paperId) references Papers (id)
	on update cascade
	on delete cascade
)

create table ConferenceSessionsPapers(
	sessionId int not null,
	paperId int not null,
	constraint PK_ConferenceSessionsPapers primary key (sessionId, paperId),
	constraint FK_ConferenceSessionsPapers_ConferenceSessions foreign key (sessionId) references ConferenceSessions (id)
	on update cascade
	on delete cascade,
	constraint FK_ConferenceSessionsPapers_Papers foreign key (paperId) references Papers (id)
	on update cascade
	on delete cascade
)

create table ConflictsOfInterest(
	id int primary key identity(1, 1),
	paperId int not null,
	[description] text,
	reviewerId int not null,
	constraint FK_ConflictsOfInterest_Papers foreign key (paperId) references Papers (id)
	on update cascade
	on delete cascade,
	constraint FK_ConflictsOfInterest_Reviewers foreign key (reviewerId) references Reviewers (id)
	on update cascade
	on delete cascade,
)

create table BidsForPapers(
	reviewerId int not null,
	paperId int not null,
	interest int,
	constraint PK_BidsForPapers primary key (reviewerId, paperId),
	constraint FK_BidsForPapers_Reviewers foreign key (reviewerId) references Reviewers (id)
	on update cascade
	on delete cascade,
	constraint FK_BidsForPapers_Papers foreign key (paperId) references Papers (id)
	on update cascade
	on delete cascade
)

create table AssignedPapers(
	reviewerId int not null,
	paperId int not null,
	constraint PK_AssignedPapers primary key (reviewerId, paperId),
	constraint FK_AssignedPapers_Reviewers foreign key (reviewerId) references Reviewers (id)
	on update cascade
	on delete cascade,
	constraint FK_AssignedPapers_Papers foreign key (paperId) references Papers (id)
	on update cascade
	on delete cascade
)

create table PapersReviewersComments(
	paperId int not null,
	reviewerId int not null,
	content text not null,
	constraint PK_PapersReviewersComments primary key (paperId, reviewerId),
	constraint FK_PapersReviewersComments_Papers foreign key (paperId) references Papers (id)
	on update cascade
	on delete cascade,
	constraint FK_PapersReviewersComments_Reviewers foreign key (reviewerId) references Reviewers (id)
	on update cascade
	on delete cascade
)

create table ChairsEvaluations(
	chairId int not null,
	paperId int not null,
	judgement varchar(15),
	constraint PK_ChairsEvaluations primary key (chairId, paperId),
	constraint FK_ChairsEvaluations_Chairs foreign key (chairId) references Chairs (id)
	on update cascade
	on delete cascade,
	constraint FK_ChairsEvaluations_Papers foreign key (paperId) references Papers (id)
	on update cascade
	on delete cascade
)

create table ReviewersEvaluations(
	reviewerId int not null,
	paperId int not null,
	judgement varchar(15),
	constraint PK_ReviewersEvaluations primary key (reviewerId, paperId),
	constraint FK_ReviewersEvaluations_Reviewers foreign key (reviewerId) references Reviewers (id)
	on update cascade
	on delete cascade,
	constraint FK_ReviewersEvaluations_Papers foreign key (paperId) references Papers (id)
	on update cascade
	on delete cascade
)



