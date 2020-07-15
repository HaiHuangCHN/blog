package com.hai.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hai.entity.Entry;
import com.hai.repository.EntryRepository;

@Service
public class EntryService {

	Timestamp currentTs = new Timestamp(System.currentTimeMillis());

	@Autowired
	private EntryRepository entryRepository;

	public List<Entry> getAllEntries() {
		List<Entry> entries = entryRepository.finaAll();
		return entries;
	}

	public void addEntry(String title, String text) {
		Entry entry = new Entry(title, text);
		entry.setCreatedDate(currentTs);
		entry.setUpdatedDate(currentTs);
		entryRepository.save(entry);
	}

}
