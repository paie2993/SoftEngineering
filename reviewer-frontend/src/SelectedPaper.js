import { useState, useEffect } from 'react'
import styles from './SelectedPaper.module.css'
import { PropTypes } from 'prop-types'

function SelectedPaperTitle({ paperTitle }) {
    return <h1 className={styles.selectedPaperTitle}>{paperTitle}</h1>
}

function PaperAuthorItem({ paperAuthor }) {
    return (
        <div className={styles.paperAuthor}>
            <p>{paperAuthor.name}</p>
        </div>
    )
}

function SelectedPaperAuthorList({ paperAuthors }) {
    return (
        <div className={styles.authorList}>
            {paperAuthors.length > 0
            ?
            paperAuthors.map((author, index) => <PaperAuthorItem key={index} paperAuthor={author} />)
            :
            <p>The authors of this papers could not be found</p>
            }
        </div>
    )
}

function SelectedPaperMainDetails({ paperTitle, paperAbstract, paperTopics, paperAuthors }) {

    const stringedTopics = stringifyTopics(paperTopics)

    return (
        <form className={styles.mainDetails}>
            <div className={styles.pair}>
                <label htmlFor="title" className={styles.detailsLabel}>
                    Title
                </label>
                <input className={styles.detailsInput} id="title" type="text" value={paperTitle} readOnly />
            </div>
            <div className={styles.pair}>
                <label htmlFor="abstract" className={styles.detailsLabel}>
                    Abstract
                </label>
                <input className={styles.detailsInput} id="abstract" type="text" value={paperAbstract} readOnly />
            </div>
            <div className={styles.pair}>
                <label htmlFor="topics" className={styles.detailsLabel}>
                    Topics
                </label>
                <input className={styles.detailsInput} id="topics" type="text" value={stringedTopics} readOnly />
            </div>
            <div className={styles.pair}>
                <label htmlFor="authors" className={styles.detailsLabel}>
                    Authors
                </label>
                <SelectedPaperAuthorList paperAuthors={paperAuthors} />
            </div>
        </form>
    );
}

function PaperCommentItem({ paperComment }) {
    return (
        <div className={styles.paperComment}>
            <p>{paperComment.content}</p>
        </div>
    )
}

function SelectedPaperCommentList({ paperComments }) {
    return (
        <div className={styles.commentsList}>
            {paperComments.length > 0
            ?
            paperComments.map((comment, index) => <PaperCommentItem key={index} paperComment={comment} />)
            :
            <p>There haven't been found any comments for the paper</p>
            }
        </div>
    )
}

function SelectedPaperCommentContainer({ paperComments }) {
    return (
        <div className={styles.paperComments}>
            <label className={styles.commentsLabel} htmlFor="commentsList">Comments</label>
            <SelectedPaperCommentList id="commentsList" paperComments={paperComments} />
        </div>
    )
}

function SelectedPaperContent({ paperTitle, paperAbstract, paperTopics, paperAuthors, paperComments }) {
    return (
        <div className={styles.selectedPaperContent}>
            <SelectedPaperMainDetails 
            paperTitle={paperTitle} 
            paperAbstract={paperAbstract}
            paperTopics={paperTopics} 
            paperAuthors={paperAuthors} />
            <SelectedPaperCommentContainer 
            paperComments={paperComments} />
        </div>
    );
}

function PaperDetailsPage({ paperId }) {

    const [ paper, setPaper ] = useState({
        id: 0,
        title: "",
        abstract: "No abstract"
    })
    const [ paperTopics, setPaperTopics ] = useState([])
    const [ paperAuthors, setPaperAuthors ] = useState([])
    const [ paperComments, setPaperComments ] = useState([])

    const fetchPaper = async () => {
        const response = await fetch(`http://localhost/SoftEngApplication/reviewer-backend/fetch-paper-by-id.php/?id=${paperId}`)
        const data = response.json()
        return Promise.resolve(data)
    }

    // expects an array of topics, not a string of concatened stringified topics
    const fetchPaperTopics = async () => {
        const response = await fetch(`http://localhost/SoftEngApplication/reviewer-backend/fetch-paper-topics-by-paper-id.php/?id=${paperId}`)
        const data = response.json()
        return Promise.resolve(data)
    }

    // expects an array of authors
    const fetchPaperAuthors = async () => {
        const response = await fetch(`http://localhost/SoftEngApplication/reviewer-backend/fetch-paper-authors-by-paper-id.php/?id=${paperId}`)
        const data = response.json()
        return Promise.resolve(data)
    }

    // expects an array of comments
    const fetchPaperComments = async () => {
        const response = await fetch(`http://localhost/SoftEngApplication/reviewer-backend/fetch-paper-comments-by-paper-id.php/?id=${paperId}`)
        const data = response.json()
        return Promise.resolve(data)
    }

    const getPaper = () => {
        fetchPaper().then(data => setPaper(data))
    }

    const getPaperTopics = () => {
        fetchPaperTopics().then(data => setPaperTopics(data))
    }

    const getPaperAuthors = () => {
        fetchPaperAuthors().then(data => setPaperAuthors(data))
    }

    const getPaperComments = () => {
        fetchPaperComments().then(data => setPaperComments(data))
    }

    useEffect(() => {
        getPaper()
    }, [])

    useEffect(() => {
        getPaperTopics()
    }, [])

    useEffect(() => {
        getPaperAuthors()
    }, [])

    useEffect(() => {
        getPaperComments()
    }, [])

    return (
        <div className={styles.paperDetailsDiv}>
            <SelectedPaperTitle paperTitle={paper.title} />
            <div className={styles.selectedPaperSeparator}></div>
            <SelectedPaperContent 
            paperTitle={paper.title} 
            paperAbstract={paper.abstract} 
            paperTopics={paperTopics} 
            paperAuthors={paperAuthors} 
            paperComments={paperComments}/>
        </div>
    );
}

PaperDetailsPage.defaultProps = {
    paperId: 0
}

// required for the 'Topics' field of the read-only form of the page
function stringifyTopics(topics) {
    let stringed = ""
    if (topics.length > 0) {
        topics.forEach((topic) => {
            if (stringed.length === 0) {
                stringed = stringed + topic.name
            } else {
                stringed = stringed + ", " + topic.name
            }
        })
    }
    return stringed
}

export { PaperDetailsPage }