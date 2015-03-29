using System;
using System.Collections;
using System.Runtime.Remoting.Messaging;

namespace AC.Code.DbObjects
{
    /// <summary>
    /// ���ݻ��汣����Ϣ�첽����ί��
    /// </summary>
    internal delegate void EventSaveCache(object key, object value);

    /// <summary>
    /// ���󻺴���
    /// </summary>
    public class Cache
    {
        // ���ڻ������ݵ�Hashtable       
        protected Hashtable _Cache = new Hashtable();
        protected Object LockObj = new object();

        public int Count
        {
            get { return _Cache.Count; }
        }

        #region ��ȡָ����ֵ�Ķ���GetObject()

        /// <summary>
        /// ��ȡָ����ֵ�Ķ���
        /// </summary>
        /// <param name="key">��ֵ</param>
        /// <returns>object</returns>
        public virtual object GetObject(object key)
        {
            if (_Cache.ContainsKey(key))
            {
                return _Cache[key];
            }
            return null;
        }

        #endregion

        #region �Ѷ���ָ���ļ�ֵ���浽������SaveCaech()

        /// <summary>
        /// �Ѷ���ָ���ļ�ֵ���浽������
        /// </summary>
        /// <param name="key">��ֵ</param>
        /// <param name="value">����Ķ���</param>
        public void SaveCache(object key, object value)
        {
            EventSaveCache save = SetCache;
            IAsyncResult ar = save.BeginInvoke(key, value, Results, null);
        }

        /// <summary>
        /// �Ѷ���ָ���ļ�ֵ���浽������
        /// </summary>
        /// <param name="key">��ֵ</param>
        /// <param name="value">����Ķ���</param>
        protected virtual void SetCache(object key, object value)
        {
            lock (LockObj)
            {
                if (!_Cache.ContainsKey(key))
                    _Cache.Add(key, value);
            }
        }

        private void Results(IAsyncResult ar)
        {
            var fd = (EventSaveCache) ((AsyncResult) ar).AsyncDelegate;
            fd.EndInvoke(ar);
        }

        #endregion

        #region �ڻ�����ɾ��ָ����ֵ�Ķ���DelObject()

        /// <summary>
        /// �ڻ�����ɾ��ָ����ֵ�Ķ���
        /// </summary>
        /// <param name="key">��ֵ</param>
        public virtual void DelObject(object key)
        {
            lock (_Cache.SyncRoot)
            {
                _Cache.Remove(key);
            }
        }

        #endregion

        #region ������������ж���Clear()

        /// <summary>
        /// ������������ж���
        /// </summary>
        public virtual void Clear()
        {
            lock (_Cache.SyncRoot)
            {
                _Cache.Clear();
            }
        }

        #endregion
    }
}