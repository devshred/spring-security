package org.devshred.service.impl;

import com.google.common.collect.ImmutableList;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;

import java.util.List;

import org.devshred.service.Area51Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class Area51ServiceImpl implements Area51Service {
	@Override
	public String greeting() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	@Override
	public List<String> status() {
		final ImmutableList.Builder<String> builder = ImmutableList.builder();

		builder.add(String.format("Heap: %s", ManagementFactory.getMemoryMXBean().getHeapMemoryUsage()));
		builder.add(String.format("NonHeap: %s", ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage()));

		final List<MemoryPoolMXBean> beans = ManagementFactory.getMemoryPoolMXBeans();
		for (final MemoryPoolMXBean bean : beans) {
			builder.add(String.format("%s: %s ", bean.getName(), bean.getUsage()));
		}
		for (final GarbageCollectorMXBean bean : ManagementFactory.getGarbageCollectorMXBeans()) {
			builder.add(String.format("%s: count=%s, time=%s", bean.getName(), bean.getCollectionCount(),
					bean.getCollectionTime()));
		}

		return builder.build();
	}
}
