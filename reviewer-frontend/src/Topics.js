import styles from './Topics.module.css'
import { useState, useEffect } from 'react'

function Topic({ userTopic, onDeleteClick }) {
    return (
        <div className={styles.topicDiv}>
            <div className={styles.topicHeader}>
                <h3>{userTopic.name}</h3>
                <p 
                className={styles.deleteX} 
                onClick={() => onDeleteClick(userTopic.id)}>
                X</p>
            </div>
            <p>{userTopic.description}</p>
        </div>
    )
}

function TopicList({ userTopics, onDeleteClick }) {
    return (
        <div className={styles.topicListDiv}>
            {userTopics.length > 0 
            ? 
            userTopics.map((userTopic, index) => <Topic 
                                                    key={index}
                                                    userTopic={userTopic} 
                                                    onDeleteClick={onDeleteClick}/>) 
            :
            <p>You don't have any topics of interest</p>}
        </div>
    )
}

function ListOption({ notUserTopic, onAdd }) {
    return <option 
            value={notUserTopic.name}
            onClick={() => onAdd(notUserTopic.id)}>
            {notUserTopic.name}</option>
}

function AddOptions({ notUserTopics, onAdd }) {
    return (
        <select className={styles.addSelect}>
            {notUserTopics.map((topic, index) => 
                <ListOption 
                key={index} 
                notUserTopic={topic} 
                onAdd={onAdd} />)}
        </select>
    );
}

function TableModifiers({ notUserTopics, setNotUserTopics, onAdd }) {
    return (
        <div className={styles.tableModifiers}>
            <AddOptions notUserTopics={notUserTopics} setNotUserTopics={setNotUserTopics} onAdd={onAdd} />
        </div>
    );
}

function TopicsContent({ userTopics, notUserTopics, setNotUserTopics, onDeleteClick, onAdd }) {
    return (
        <div className={styles.topicsContent}>
            <TopicList userTopics={userTopics} onDeleteClick={onDeleteClick}/>
            <TableModifiers notUserTopics={notUserTopics} setNotUserTopics={setNotUserTopics} onAdd={onAdd} />
        </div>
    );
}


function TopicsPage({ userId }) {

    const [userTopics, setUserTopics] = useState([])
    const [notUserTopics, setNotUserTopics] = useState([])

    const fetchUserTopics = async () => {
        const response = await fetch(`http://localhost/SoftEngApplication/reviewer-backend/fetch-user-topics.php/?id=${userId}`)
        const data = await response.json()
        return Promise.resolve(data)
    }

    const fetchNotTopics = async () => {
        const response = await fetch(`http://localhost/SoftEngApplication/reviewer-backend/fetch-not-topics.php/?id=${userId}`)
        const data = await response.json()
        return Promise.resolve(data)
    }

    const getTopics = () => {
        fetchUserTopics().then((data) => setUserTopics(data))
        fetchNotTopics().then((data) => setNotUserTopics(data))
    }

    useEffect(() => {
        getTopics()
    }, [])

    const onDeleteClick = async (userTopicId) => {
        const response = await fetch(
            `http://localhost/SoftEngApplication/reviewer-backend/delete-user-topic.php/?id=${userId}&topic_id=${userTopicId}`,
            { method: 'DELETE' }
        )        
        const data = await response.json()

        if (data) {
            getTopics()
        } else {
            alert('Could not delete the topic')
        }
    }

    const onAdd = async (topicId) => {
        const response = await fetch (
            `http://localhost/SoftEngApplication/reviewer-backend/add-user-topic.php`,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ 
                    user_id: userId,
                    topic_id: topicId
                })
            }
        )
        const data = response.json()

        if (data) {
            getTopics()
        } else {
            alert('Could not add the topic')
        }
    }

    return (
        <div className={styles.topicsContainer}>
            <h1 className={styles.topicsHeader}>My topics of interest</h1>
            <div className={styles.separator}></div>
            <TopicsContent 
            userTopics={userTopics} 
            setUserTopics={setUserTopics} 
            notUserTopics={notUserTopics}
            setNotUserTopics={setNotUserTopics}
            onDeleteClick={onDeleteClick}
            onAdd={onAdd}/>
        </div>
    );
}


export { TopicsPage }