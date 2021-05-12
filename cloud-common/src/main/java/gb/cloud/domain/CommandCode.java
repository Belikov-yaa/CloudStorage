package gb.cloud.domain;

/**
 * Перечень всех команд
 */

public enum CommandCode {
    AUTHENTICATE,
    LIST,
    CMD_SUCCESS,
    CMD_FAIL,
    DOWNLOAD,
    UPLOAD,
    FILES_REQUEST,
    FILES_OFFER,
    UPLOAD_READY,
    DOWNLOAD_READY,
    DOWNLOAD_POSSIBLE,
    UPLOAD_POSSIBLE
}
